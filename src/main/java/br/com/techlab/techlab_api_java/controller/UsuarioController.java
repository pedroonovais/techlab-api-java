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

import br.com.techlab.techlab_api_java.model.Usuario;
import br.com.techlab.techlab_api_java.repository.UsuarioRepository;
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
@RequestMapping("/usuario")
@Slf4j
@Cacheable(value = "usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    @Cacheable
    @Operation(
        summary = "Retorna todos os usuários",
        description = "Retorna todos os usuários cadastrados no sistema, sendo possível paginar e ordenar"
    )
    public List<Usuario> index() {
        log.info("Listando todos os usuários");
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
    public Usuario create(@RequestBody @Valid Usuario usuario) {
        log.info("Cadastrando usuário: " + usuario.getNome());
        usuario.setDataCadastro(LocalDateTime.now());
        usuario.setDataAtualizacao(LocalDateTime.now());
        return repository.save(usuario);
    }

    @GetMapping("{id}")
    public Usuario get(@PathVariable Long id) {
        log.info("Buscando usuário: " + id);
        return getUsuario(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando usuário " + id);
        repository.delete(getUsuario(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true)
    public Usuario update(@PathVariable Long id, @RequestBody Usuario usuario) {
        log.info("Atualizando usuário " + id + " " + usuario);

        getUsuario(id);
        usuario.setId(id);
        usuario.setDataAtualizacao(LocalDateTime.now());
        return repository.save(usuario);
    }

    private Usuario getUsuario(Long id) {
        return repository
            .findById(id)
            .orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário " + id + " não encontrado")
            );
    }
}
