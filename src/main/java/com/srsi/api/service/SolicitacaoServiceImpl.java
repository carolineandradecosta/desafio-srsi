package com.srsi.api.service;

import com.srsi.api.dto.SolicitacaoRequestDTO;
import com.srsi.api.dto.SolicitacaoResponseDTO;
import com.srsi.api.enums.StatusEnum;
import com.srsi.api.mapper.SolicitacaoMapper;
import com.srsi.api.model.Solicitacao;
import com.srsi.api.repository.SolicitacaoRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitacaoServiceImpl implements SolicitacaoService{

    private final SolicitacaoRepository solicitacaoRepository;
    private final SolicitacaoMapper solicitacaoMapper;

    @Override
    public SolicitacaoResponseDTO criarSolicitacao(SolicitacaoRequestDTO dto) {
        Solicitacao solicitacao = solicitacaoMapper.toEntity(dto);
        solicitacao.setStatus(StatusEnum.ABERTA);
        return solicitacaoMapper.toDTO(solicitacaoRepository.save(solicitacao));
    }

    @Override
    public List<SolicitacaoResponseDTO> listarTodasSolicitacoes() {
        return solicitacaoRepository.findAll().stream()
                .map(solicitacaoMapper :: toDTO)
                .toList();
    }

    @Override
    public SolicitacaoResponseDTO buscarSolicitacaoPorId(Long id) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Cliente não encontrado"));
        return solicitacaoMapper.toDTO(solicitacao);
    }

    @Transactional
    @Override
    public SolicitacaoResponseDTO atualizarSolicitacao(Long id, SolicitacaoRequestDTO dto) {
        Solicitacao solicitacao = solicitacaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado"));

        if(!solicitacao.getStatus().equals(StatusEnum.CONCLUIDA)){
            solicitacao.setTitulo(dto.titulo());
            solicitacao.setDescricao(dto.descricao());
            solicitacao.setEmail(dto.email());
            solicitacao.setStatus(dto.status());

        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "O Status não pode ser alterado");
        }

        return solicitacaoMapper.toDTO(solicitacaoRepository.save(solicitacao));
    }

    @Transactional
    @Override
    public void deletarSolicitacao(Long id) {
        if(!solicitacaoRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cliente não encontrado");
        }
        solicitacaoRepository.deleteById(id);

    }
}
