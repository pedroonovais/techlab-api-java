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

import br.com.techlab.techlab_api_java.dto.UsuarioRequest;
import br.com.techlab.techlab_api_java.model.Usuario;
import br.com.techlab.techlab_api_java.model.UsuarioFilter;
import br.com.techlab.techlab_api_java.repository.UsuarioRepository;
import br.com.techlab.techlab_api_java.specification.UsuarioSpecfication;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping("/usuario")
@Slf4j
@Cacheable(value = "usuario")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @GetMapping
    @Cacheable(value = "usuario")
    @Operation(
        summary = "Retorna todos os usuários",
        description = "Retorna todos os usuários cadastrados no sistema, sendo possível paginar e ordenar"
    )
    public Page<Usuario> index(UsuarioFilter filter, @PageableDefault(size = 10, sort = "id", direction = Direction.ASC) Pageable pageable) {
        log.info("Listando todos os usuários");
        return repository.findAll(UsuarioSpecfication.withFilters(filter), pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CacheEvict(allEntries = true, value = "usuario")
    public Usuario create(@RequestBody @Valid UsuarioRequest dto) {
        System.out.println( dto);
        log.info("Cadastrando usuário: " + dto.nome());

        Usuario usuario = Usuario.builder()
            .nome(dto.nome())
            .email(dto.email())
            .senha(dto.senha())
            .cpf(dto.cpf())
            .status(dto.status())
            .perfil(dto.perfil())
            .dataCadastro(LocalDateTime.now())
            .dataAtualizacao(LocalDateTime.now())
            .build();

        return repository.save(usuario);
    }
    
    @GetMapping("{id}")
    public Usuario get(@PathVariable Long id) {
        log.info("Buscando usuário: " + id);
        return getUsuario(id);
    }

    @DeleteMapping("{id}")
    @CacheEvict(allEntries = true, value = "usuario")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void destroy(@PathVariable Long id) {
        log.info("Apagando usuário " + id);
        repository.delete(getUsuario(id));
    }

    @PutMapping("{id}")
    @CacheEvict(allEntries = true, value = "usuario")
    public Usuario update(@PathVariable Long id, @RequestBody @Valid Usuario usuario) {
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
