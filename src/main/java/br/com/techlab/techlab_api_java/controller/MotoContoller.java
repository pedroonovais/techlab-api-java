package br.com.techlab.techlab_api_java.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.techlab.techlab_api_java.model.Moto;
import br.com.techlab.techlab_api_java.model.MotoFilter;
import br.com.techlab.techlab_api_java.repository.MotoRepository;
import br.com.techlab.techlab_api_java.specification.MotoSpecification;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/moto")
@Slf4j
@Cacheable(value = "moto")
public class MotoContoller {
    
    @Autowired
    private MotoRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Retorna todas as motos",
        description = "Retorna todas as motos cadastradas no sistema, sendo possivel paginar e ordenar"
    )
    public Page<Moto> index(MotoFilter filter, @PageableDefault(size = 10, sort = "placa", direction = Direction.ASC) Pageable pageable) {
        log.info("Listando todas as motos");
        return repository.findAll(MotoSpecification.withFilters(filter), pageable);
    }

    @PostMapping
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public Moto create(@RequestBody @Valid Moto moto) {
        log.info("Cadastrando moto " + moto.getPlaca());
        return repository.save(moto);
    }

    @GetMapping("{id}")
    public Moto get(@PathVariable Long id) {
        log.info("Buscando moto: " + id);
        return getMoto(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando moto " + id);
        repository.delete(getMoto(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    public Moto update(@PathVariable Long id, @RequestBody Moto moto) {
        log.info("Atualizando moto " + id + " " + moto);	

        getMoto(id);
        moto.setId(id);
        return repository.save(moto);
    }

    private Moto getMoto(Long id){
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Moto " + id + " não encontrada")
            );
    }
}
