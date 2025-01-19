package io.github.adrianovictorn.syshelp.dtos;

import java.time.LocalDateTime;

import io.github.adrianovictorn.syshelp.entity.Call;
import io.github.adrianovictorn.syshelp.entity.Enums.Ocorrencia;
import io.github.adrianovictorn.syshelp.entity.Enums.Setores;
import io.github.adrianovictorn.syshelp.entity.Enums.Status;

public record ListCallDTO(
    Long id,
    String solicitante,
    Setores setor,
    String descricao,
    LocalDateTime horarioSolicitado,
    LocalDateTime horarioFinalizado,
    Status status,
    String numero,
    Ocorrencia ocorrencia
) {

    public static ListCallDTO fromEntity(Call chamado){
       return new ListCallDTO(
        chamado.getId(),
        chamado.getSolicitante(), 
        chamado.getSetor(), 
        chamado.getDescricao(),
        chamado.getHorarioSolicitado(),
        chamado.getHorarioFinalizado(),
        chamado.getStatus(),
        chamado.getNumeroParaContato(),
        chamado.getOcorrencia());
    }
    
}
