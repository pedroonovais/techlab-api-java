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
import br.com.techlab.techlab_api_java.repository.PatioRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable
    @Operation(
        summary = "Retorna todos os patios",
        description = "Retorna todos os patios cadastrados no sistema, sendo possível paginar e ordenar",
        tags = {"Patio"}
    )
    public List<Patio> index() {
        log.info("Listando todos os patios");
        return repository.findAll();
    }

    @PostMapping
    @CacheEvict(allEntries = true)
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
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando patio " + id);
        repository.delete(getPatio(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
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
