package br.com.techlab.techlab_api_java.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank()
    private String codigoIdentificacao;

    private SensorType tipo;

    @NotBlank()
    private String localizacaoFisica;

    private StatusType status;

    @PastOrPresent(message = "A data de cadastro não pode ser no futuro.")
    private LocalDateTime dataCadastro;

    @PastOrPresent(message = "A data de atualização não pode ser no futuro.")
    private LocalDateTime dataAtualizacao;
}
