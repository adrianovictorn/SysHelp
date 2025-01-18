package io.github.adrianovictorn.syshelp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.adrianovictorn.syshelp.entity.Call;

public interface CallRepository extends JpaRepository<Call,Long> {
    
}
