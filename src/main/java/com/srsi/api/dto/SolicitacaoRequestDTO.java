package com.srsi.api.dto;

import com.srsi.api.enums.StatusEnum;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SolicitacaoRequestDTO(

        @NotBlank(message = "Título é obrigatório")
        @Size(max = 150)
        String titulo,

        @NotBlank(message = "Título é obrigatório")
        @Size(max = 300)
        String descricao,

        @NotBlank(message = "E-mail é obrigatório")
        @Email(message = "E-mail é inválido")
        String email,

        @NotNull(message = "O Status é obrigatório")
        StatusEnum status

) {
}
