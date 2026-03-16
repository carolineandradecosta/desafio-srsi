package com.srsi.api.mapper;

import com.srsi.api.dto.SolicitacaoRequestDTO;
import com.srsi.api.dto.SolicitacaoResponseDTO;
import com.srsi.api.model.Solicitacao;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SolicitacaoMapper {

    @Mapping(target = "id", ignore = true)
    Solicitacao toEntity(SolicitacaoRequestDTO solicitacaoRequestDTO);

    SolicitacaoResponseDTO toDTO(Solicitacao solicitacao);
}
