package br.com.techlab.techlab_api_java.model;

import java.time.LocalDateTime;

public record SensorFilter(String localizacaoFisica, LocalDateTime dataCadastroInicial, LocalDateTime dataCadastroFinal) {
}	
