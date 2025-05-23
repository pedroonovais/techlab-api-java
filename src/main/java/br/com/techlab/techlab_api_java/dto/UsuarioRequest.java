package br.com.techlab.techlab_api_java.dto;

import br.com.techlab.techlab_api_java.model.StatusType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

public record UsuarioRequest(

    @NotBlank(message = "O nome é obrigatório.")
    @Size(min = 3, message = "O nome deve ter pelo menos 3 caracteres.")
    String nome,

    @Email(message = "Email inválido.")
    String email,

    @NotBlank(message = "Senha é obrigatória.")
    @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres.")
    String senha,

    @CPF(message = "CPF inválido.")
    String cpf,

    @NotNull(message = "Status é obrigatório.")
    StatusType status,

    @NotBlank(message = "Perfil é obrigatório.")
    String perfil
) {}
