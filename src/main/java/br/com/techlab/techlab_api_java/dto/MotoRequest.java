package br.com.techlab.techlab_api_java.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record MotoRequest(

    @NotBlank(message = "Marca é obrigatória.")
    @Size(min = 3, message = "A marca deve ter pelo menos 3 caracteres.")
    String marca,

    @NotBlank(message = "Modelo é obrigatório.")
    @Size(min = 3, message = "O modelo deve ter pelo menos 3 caracteres.")
    String modelo,

    @NotBlank(message = "Placa é obrigatória.")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{4}$", message = "A placa deve seguir o formato 'AAA1234'.")
    String placa,

    @NotBlank(message = "Chassi é obrigatório.")
    String chassi,

    @NotBlank(message = "Motor é obrigatório.")
    String motor,

    @NotBlank(message = "IMEI IoT é obrigatório.")
    String imeiIot,

    Long rfId,

    LocalDateTime dataCadastro
) {}
