package br.com.techlab.techlab_api_java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.techlab.techlab_api_java.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    

}
