package com.srsi.api.service;


import com.srsi.api.dto.SolicitacaoRequestDTO;
import com.srsi.api.dto.SolicitacaoResponseDTO;

import java.util.List;

public interface SolicitacaoService {

    SolicitacaoResponseDTO criarSolicitacao(SolicitacaoRequestDTO dto);
    List<SolicitacaoResponseDTO> listarTodasSolicitacoes();
    SolicitacaoResponseDTO buscarSolicitacaoPorId(Long id);
    SolicitacaoResponseDTO atualizarSolicitacao(Long id, SolicitacaoRequestDTO dto);
    void deletarSolicitacao(Long id);

}
