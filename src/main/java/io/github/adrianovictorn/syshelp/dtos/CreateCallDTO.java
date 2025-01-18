package io.github.adrianovictorn.syshelp.dtos;

import io.github.adrianovictorn.syshelp.entity.Enums.Setores;

public record CreateCallDTO(

    String solicitante,
    Setores setor,
    String descricao,
    String numero
) {
    
}
