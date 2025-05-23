package br.com.techlab.techlab_api_java.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
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

    @Positive(message = "A capacidade deve ser positiva.")
    public int capacidadeTotal;

    @Min(1)
    @Positive(message = "As vagas disponíveis devem ser positivas.")
    public int vagasDisponiveis;

    @NotBlank(message = "Descricão é obrigatória.")
    public String descricao;

    @PastOrPresent(message = "A data de cadastro não pode ser no futuro.")
    private LocalDateTime dataCadastro;

    @PastOrPresent(message = "A data de atualização não pode ser no futuro.")
    private LocalDateTime dataAtualizacao;
}
