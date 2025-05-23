package br.com.techlab.techlab_api_java.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Marca é obrigatória.")
    @Size(min = 3, message = "A marca deve ter pelo menos 3 caracteres.")
    private String marca;

    @NotBlank(message = "Modelo é obrigatório.")
    @Size(min = 3, message = "O modelo deve ter pelo menos 3 caracteres.")
    private String modelo;

    @NotBlank(message = "Placa é obrigatória.")
    @Pattern(regexp = "^[A-Z]{3}[0-9]{4}$", message = "A placa deve seguir o formato 'AAA1234'.")
    private String placa;

    @NotBlank(message = "Chassi é obrigatório.")
    private String chassi;

    @NotBlank(message = "Motor é obrigatório.")
    private String motor;

    @NotBlank(message = "IMEI IoT é obrigatório.")
    private String imeiIot;

    @OneToOne
    private RfId rfId;

    @NotNull(message = "A data de cadastro é obrigatória.")
    @PastOrPresent(message = "A data de cadastro não pode ser no futuro.")
    private LocalDateTime dataCadastro;

    @NotNull(message = "A data de atualização é obrigatória.")
    @PastOrPresent(message = "A data de atualização não pode ser no futuro.")
    private LocalDateTime dataAtualizacao;

}