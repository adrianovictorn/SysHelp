package io.github.adrianovictorn.syshelp.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import io.github.adrianovictorn.syshelp.dtos.CreateCallDTO;
import io.github.adrianovictorn.syshelp.dtos.ListCallDTO;
import io.github.adrianovictorn.syshelp.dtos.UpdateCallDTO;
import io.github.adrianovictorn.syshelp.dtos.ViewCallDTO;
import io.github.adrianovictorn.syshelp.entity.Call;
import io.github.adrianovictorn.syshelp.entity.Enums.Status;
import io.github.adrianovictorn.syshelp.repository.CallRepository;

@Service
public class CallService {
    
    private final CallRepository repository;


    public CallService(CallRepository repository) {
        this.repository = repository;
    }

    public ViewCallDTO criarChamado(CreateCallDTO dto){
        Call novoChamado = new Call();

        novoChamado.setSolicitante(dto.solicitante());
        novoChamado.setNumeroParaContato(dto.numero());
        novoChamado.setSetor(dto.setor());
        novoChamado.setDepartamento(dto.departamento());
        novoChamado.setOcorrencia(dto.ocorrencia());
        novoChamado.setDescricao(dto.descricao());
        novoChamado.setStatus(Status.AGUARDANDO);
      

        repository.save(novoChamado);

        ViewCallDTO view = ViewCallDTO.fromEntity(novoChamado);
        return view;
    }

    public List<ListCallDTO> listarChamados(){
        return repository.findAll()
                        .stream()
                        .map(ListCallDTO::fromEntity)
                        .collect(Collectors.toList());
    }

    public ViewCallDTO atualizarChamados (Long id, UpdateCallDTO dto){
        Call chamado = repository.findById(id).orElseThrow(() -> new RuntimeException("Chamado não encontrado"));
        chamado.setStatus(dto.status());
        chamado.setHorarioFinalizado(dto.horarioFinalizado());
        repository.save(chamado);
        return ViewCallDTO.fromEntity(chamado);
    }

    public ViewCallDTO buscarChamadoPorId(Long id) {
        Call chamadoExistente = repository.findById(id).orElseThrow(() -> new RuntimeException("Chamado não encontrado!"));

        return ViewCallDTO.fromEntity(chamadoExistente);
    }


       



}
