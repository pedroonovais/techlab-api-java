package br.com.techlab.techlab_api_java.model;

import java.time.LocalDateTime;

public record UsuarioFilter(String nome, String email, String cpf, LocalDateTime dataCadastroInicial, LocalDateTime dataCadastroFinal) {
}
