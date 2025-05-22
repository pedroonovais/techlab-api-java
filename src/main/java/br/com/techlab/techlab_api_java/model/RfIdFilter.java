package br.com.techlab.techlab_api_java.model;

import java.time.LocalDate;

public record RfIdFilter(String rfId, LocalDate dataCadastroInicial, LocalDate dataCadastroFinal) {
}
