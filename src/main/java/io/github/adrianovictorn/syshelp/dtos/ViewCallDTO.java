package io.github.adrianovictorn.syshelp.dtos;

import java.time.LocalDateTime;

import io.github.adrianovictorn.syshelp.entity.Call;
import io.github.adrianovictorn.syshelp.entity.Enums.Departamento;
import io.github.adrianovictorn.syshelp.entity.Enums.Ocorrencia;
import io.github.adrianovictorn.syshelp.entity.Enums.Setores;
import io.github.adrianovictorn.syshelp.entity.Enums.Status;

public record ViewCallDTO(
    Long id,
    String solicitante,
    Departamento departamento,
    Setores setor,
    String descricao,
    LocalDateTime horarioSolicitado,
    LocalDateTime horarioFinalizado,
    Status status,
    Ocorrencia ocorrencia
) {
    public static ViewCallDTO fromEntity(Call chamado){
        return new ViewCallDTO(
            chamado.getId(),
            chamado.getSolicitante(),
            chamado.getDepartamento(),
            chamado.getSetor(),
            chamado.getDescricao(),
            chamado.getHorarioSolicitado(),
            chamado.getHorarioFinalizado(),
            chamado.getStatus(),
            chamado.getOcorrencia());
    }
}
