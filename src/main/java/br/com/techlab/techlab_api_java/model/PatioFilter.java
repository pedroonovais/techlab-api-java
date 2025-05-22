package br.com.techlab.techlab_api_java.model;

import java.time.LocalDate;

public record PatioFilter(String nome, String localizacao, LocalDate dataCadastroInicial, LocalDate dataCadastroFinal) {    
}
