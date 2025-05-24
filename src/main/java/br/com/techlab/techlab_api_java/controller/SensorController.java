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

import br.com.techlab.techlab_api_java.model.Sensor;
import br.com.techlab.techlab_api_java.model.SensorFilter;
import br.com.techlab.techlab_api_java.repository.SensorRepository;
import br.com.techlab.techlab_api_java.specification.SensorSpecfication;
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
@RequestMapping("/sensor")
@Slf4j
@Cacheable(value = "sensor")
public class SensorController {

    @Autowired
    private SensorRepository repository;

    @GetMapping
    @Cacheable(value = "sensor")
    @Operation(
        summary = "Retorna todos os sensores",
        description = "Retorna todos os sensores cadastrados no sistema, sendo possível paginar e ordenar"
    )
    public Page<Sensor> index(SensorFilter filter, @PageableDefault(size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        log.info("Listando todos os sensores");
        return repository.findAll(SensorSpecfication.withFilters(filter), pageable);
    }

    @PostMapping
    @CacheEvict(allEntries = true, value = "sensor")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
        responses = @ApiResponse(
            responseCode = "400"
        )
    )
    public Sensor create(@RequestBody @Valid Sensor sensor) {
        log.info("Cadastrando sensor: " + sensor.getCodigoIdentificacao());
        sensor.setDataCadastro(LocalDateTime.now());
        sensor.setDataAtualizacao(LocalDateTime.now());
        return repository.save(sensor);
    }

    @GetMapping("{id}")
    public Sensor get(@PathVariable Long id) {
        log.info("Buscando sensor: " + id);
        return getSensor(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true, value = "sensor")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando sensor " + id);
        repository.delete(getSensor(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true, value = "sensor")
    public Sensor update(@PathVariable Long id, @RequestBody Sensor sensor) {
        log.info("Atualizando sensor " + id + " " + sensor);

        getSensor(id);
        sensor.setId(id);
        sensor.setDataAtualizacao(LocalDateTime.now());
        return repository.save(sensor);
    }

    private Sensor getSensor(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Sensor " + id + " não encontrado")
            );
    }
}
