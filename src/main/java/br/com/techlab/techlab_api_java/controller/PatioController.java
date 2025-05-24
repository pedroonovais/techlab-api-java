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

import br.com.techlab.techlab_api_java.model.Patio;
import br.com.techlab.techlab_api_java.model.PatioFilter;
import br.com.techlab.techlab_api_java.repository.PatioRepository;
import br.com.techlab.techlab_api_java.specification.PatioSpecification;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/patio")
@Slf4j
@Cacheable(value = "patio")
public class PatioController {

    @Autowired
    private PatioRepository repository;

    @GetMapping
    @Cacheable(value = "patio")
    @Operation(
        summary = "Retorna todos os patios",
        description = "Retorna todos os patios cadastrados no sistema, sendo possível paginar e ordenar"
    )
    public Page<Patio> index(PatioFilter filter, @PageableDefault(size = 10, sort = "nome", direction = Direction.ASC) Pageable pageable) {
        log.info("Listando todos os patios");
        return repository.findAll(PatioSpecification.withFilters(filter), pageable);
    }

    @PostMapping
    @CacheEvict(allEntries = true, value = "patio")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public Patio create(@RequestBody @Valid Patio patio) {
        log.info("Cadastrando patio: " + patio.getNome());
        patio.setDataCadastro(LocalDateTime.now());
        patio.setDataAtualizacao(LocalDateTime.now());
        return repository.save(patio);
    }

    @GetMapping("{id}")
    public Patio get(@PathVariable Long id) {
        log.info("Buscando patio: " + id);
        return getPatio(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true, value = "patio")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando patio " + id);
        repository.delete(getPatio(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true, value = "patio")
    public Patio update(@PathVariable Long id, @RequestBody Patio patio) {
        log.info("Atualizando patio " + id + " " + patio);

        getPatio(id);
        patio.setId(id);
        patio.setDataAtualizacao(LocalDateTime.now());
        return repository.save(patio);
    }

    private Patio getPatio(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Patio " + id + " não encontrado")
            );
    }
}
