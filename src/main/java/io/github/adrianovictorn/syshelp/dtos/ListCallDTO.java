package io.github.adrianovictorn.syshelp.dtos;

import java.time.LocalDateTime;

import io.github.adrianovictorn.syshelp.entity.Call;
import io.github.adrianovictorn.syshelp.entity.Enums.Ocorrencia;
import io.github.adrianovictorn.syshelp.entity.Enums.Setores;
import io.github.adrianovictorn.syshelp.entity.Enums.Status;
import io.github.adrianovictorn.syshelp.entity.Enums.Departamento;


public record ListCallDTO(
    Long id,
    String solicitante,
    Departamento departamento,
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
        chamado.getDepartamento(),
        chamado.getSetor(), 
        chamado.getDescricao(),
        chamado.getHorarioSolicitado(),
        chamado.getHorarioFinalizado(),
        chamado.getStatus(),
        chamado.getNumeroParaContato(),
        chamado.getOcorrencia());
    }
    
}
