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

import br.com.techlab.techlab_api_java.model.RfId;
import br.com.techlab.techlab_api_java.repository.RfIdRepository;
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
@RequestMapping("/rfid")
@Slf4j
@Cacheable(value = "rfid")
public class RfIdController {

    @Autowired
    private RfIdRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Retorna todos os RFIDs",
        description = "Retorna todos os RFIDs cadastrados no sistema, sendo possível paginar e ordenar",
        tags = {"RfId"}
    )
    public List<RfId> index() {
        log.info("Listando todos os RFIDs");
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
    public RfId create(@RequestBody @Valid RfId rfId) {
        log.info("Cadastrando RFID: " + rfId.getCodigoIdentificacao());
        rfId.setDataCadastro(LocalDateTime.now());
        rfId.setDataAtualizacao(LocalDateTime.now());
        return repository.save(rfId);
    }

    @GetMapping("{id}")
    public RfId get(@PathVariable Long id) {
        log.info("Buscando RFID: " + id);
        return getRfId(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando RFID " + id);
        repository.delete(getRfId(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    public RfId update(@PathVariable Long id, @RequestBody RfId rfId) {
        log.info("Atualizando RFID " + id + " " + rfId);

        getRfId(id);
        rfId.setId(id);
        rfId.setDataAtualizacao(LocalDateTime.now());
        return repository.save(rfId);
    }

    private RfId getRfId(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "RfId " + id + " não encontrado")
            );
    }
}
