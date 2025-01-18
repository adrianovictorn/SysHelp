package io.github.adrianovictorn.syshelp.dtos;

import java.time.LocalDateTime;

import io.github.adrianovictorn.syshelp.entity.Call;
import io.github.adrianovictorn.syshelp.entity.Enums.Setores;
import io.github.adrianovictorn.syshelp.entity.Enums.Status;

public record ViewCallDTO(
    Long id,
    String solicitante,
    Setores setor,
    String descricao,
    LocalDateTime horarioSolicitado,
    LocalDateTime horarioFinalizado,
    Status status
) {
    public static ViewCallDTO fromEntity(Call chamado){
        return new ViewCallDTO(
            chamado.getId(),
            chamado.getSolicitante(),
            chamado.getSetor(),
            chamado.getDescricao(),
            chamado.getHorarioSolicitado(),
            chamado.getHorarioFinalizado(),
            chamado.getStatus());
    }
}
