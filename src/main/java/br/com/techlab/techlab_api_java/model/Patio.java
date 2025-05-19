package br.com.techlab.techlab_api_java.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
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
public class Patio {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
    private String nome;

    @NotBlank(message = "Localização é obrigatória.")
    @Size(min = 3, message = "A localização deve ter pelo menos 3 caracteres.")
    private String localizacao;

    @NotBlank(message = "Capacidade é obrigatória.")
    @Positive(message = "A capacidade deve ser positiva.")
    public int capacidadeTotal;

    @NotBlank(message = "Vagas disponíveis é obrigatória.")
    @Positive(message = "As vagas disponíveis devem ser positivas.")
    public int vagasDisponiveis;

    @NotBlank(message = "Descricão é obrigatória.")
    public String descricao;

    @NotNull(message = "A data de cadastro é obrigatória.")
    @PastOrPresent(message = "A data de cadastro não pode ser no futuro.")
    private LocalDateTime dataCadastro;

    @NotNull(message = "A data de atualização é obrigatória.")
    @PastOrPresent(message = "A data de atualização não pode ser no futuro.")
    private LocalDateTime dataAtualizacao;
}
