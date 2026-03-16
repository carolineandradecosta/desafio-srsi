package com.srsi.api.controller;

import com.srsi.api.dto.SolicitacaoRequestDTO;
import com.srsi.api.dto.SolicitacaoResponseDTO;
import com.srsi.api.service.SolicitacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/solicitacoes")
@RequiredArgsConstructor
public class SolicitacaoController {

    private final SolicitacaoService solicitacaoService;

    @PostMapping
    public ResponseEntity<SolicitacaoResponseDTO> criarCliente(@Valid @RequestBody SolicitacaoRequestDTO dto){
        SolicitacaoResponseDTO solicitacaoResponseDTO = solicitacaoService.criarSolicitacao(dto);
        return ResponseEntity.status(201).body(solicitacaoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<SolicitacaoResponseDTO>> listarTodosClientes (){
        return ResponseEntity.ok(solicitacaoService.listarTodasSolicitacoes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> buscarClientePeloId (@PathVariable Long id) {
        return ResponseEntity.ok(solicitacaoService.buscarSolicitacaoPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitacaoResponseDTO> atualizarClientePeloId
            (@PathVariable Long id,
             @Valid @RequestBody SolicitacaoRequestDTO dto){
        return ResponseEntity.ok(solicitacaoService.atualizarSolicitacao(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarClientePeloId(@PathVariable Long id){
        solicitacaoService.deletarSolicitacao(id);
        return ResponseEntity.noContent().build();
    }

}
