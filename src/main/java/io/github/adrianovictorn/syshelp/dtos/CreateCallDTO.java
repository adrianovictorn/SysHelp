package io.github.adrianovictorn.syshelp.dtos;

import io.github.adrianovictorn.syshelp.entity.Enums.Ocorrencia;
import io.github.adrianovictorn.syshelp.entity.Enums.Setores;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.github.adrianovictorn.syshelp.entity.Enums.Departamento;


public record CreateCallDTO(

    @NotBlank String solicitante,
    @NotNull String numero,
    @NotBlank Departamento departamento,
    Setores setor,
    @NotBlank String descricao,
    @NotNull Ocorrencia ocorrencia

) {
    
}
