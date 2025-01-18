package io.github.adrianovictorn.syshelp.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import io.github.adrianovictorn.syshelp.entity.Enums.Setores;
import io.github.adrianovictorn.syshelp.entity.Enums.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Chamados")
public class Call {
    
    public Call() {
    }

    public Call(Long id, String solicitante, Setores setor, String descricao, LocalDateTime horarioSolicitado,
            LocalDateTime horarioFinalizado, Status status, String numero) {
        this.id = id;
        this.solicitante = solicitante;
        this.setor = setor;
        this.descricao = descricao;
        this.horarioSolicitado = horarioSolicitado;
        this.horarioFinalizado = horarioFinalizado;
        this.status = status;
        this.numeroParaContato = numero;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Solicitante", length = 50)
    private String solicitante;

    @Enumerated(EnumType.STRING)
    private Setores setor;

    @Column(name = "Descricao", length = 150)
    private String descricao;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime horarioSolicitado;

    @Column(name = "Finalizacao")
    private LocalDateTime horarioFinalizado;

    @Enumerated(EnumType.STRING)
    @Column(name = "Status")
    private Status status = Status.AGUARDANDO;

    @Column (name = "Numero", length = 15)
    private String numeroParaContato;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }

    public Setores getSetor() {
        return setor;
    }

    public void setSetor(Setores setor) {
        this.setor = setor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDateTime getHorarioSolicitado() {
        return horarioSolicitado;
    }

    public void setHorarioSolicitado(LocalDateTime horarioSolicitado) {
        this.horarioSolicitado = horarioSolicitado;
    }

    public LocalDateTime getHorarioFinalizado() {
        return horarioFinalizado;
    }

    public void setHorarioFinalizado(LocalDateTime horarioFinalizado) {
        this.horarioFinalizado = horarioFinalizado;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNumeroParaContato() {
        return numeroParaContato;
    }

    public void setNumeroParaContato(String numeroParaContato) {
        this.numeroParaContato = numeroParaContato;
    }

    
    

}
