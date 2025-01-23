package io.github.adrianovictorn.syshelp.dtos;

import io.github.adrianovictorn.syshelp.entity.Enums.Ocorrencia;
import io.github.adrianovictorn.syshelp.entity.Enums.Setores;
import io.github.adrianovictorn.syshelp.entity.Enums.Departamento;


public record CreateCallDTO(

    String solicitante,
    String numero,
    Setores setor,
    Departamento departamento,
    String descricao,
    Ocorrencia ocorrencia

) {
    
}
