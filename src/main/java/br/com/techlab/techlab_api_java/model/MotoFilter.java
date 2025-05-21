package br.com.techlab.techlab_api_java.model;

import java.time.LocalDate;

public record ModelFilter(String marca, String placa, String modelo, String chassi, String motor, 
    String imeiIot, LocalDate dataCadastroInicial, LocalDate dataCadastroFinal) {
}
