package com.srsi.api.dto;

import java.time.LocalDateTime;

public record SolicitacaoResponseDTO(
        Long id,
        String titulo,
        String descricao,
        String cpf,
        LocalDateTime criadoEm
) {
}
