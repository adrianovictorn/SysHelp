package io.github.adrianovictorn.syshelp.dtos;

import java.time.LocalDateTime;

import io.github.adrianovictorn.syshelp.entity.Enums.Status;

public record UpdateCallDTO(
   Status status,
   LocalDateTime horarioFinalizado

) {
    
}
