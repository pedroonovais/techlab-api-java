package br.com.techlab.techlab_api_java.model;

import java.time.LocalDateTime;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
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
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório.")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
    private String nome;

    @Email()
    private String email;

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    private String senha;

    @CPF()
    public String cpf;

    @NotBlank()    
    private StatusType status;

    @NotBlank(message = "Perfil é obrigatório.")
    private String perfil;

    @NotBlank()
    private AreaType area;

    @NotNull(message = "A data de cadastro é obrigatória.")
    @PastOrPresent(message = "A data de cadastro não pode ser no futuro.")
    private LocalDateTime dataCadastro;
    
    @NotNull(message = "A data de atualização é obrigatória.")
    @PastOrPresent(message = "A data de atualização não pode ser no futuro.")
    private LocalDateTime dataAtualizacao;


}
