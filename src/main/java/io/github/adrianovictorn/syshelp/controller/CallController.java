package io.github.adrianovictorn.syshelp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.adrianovictorn.syshelp.dtos.CreateCallDTO;
import io.github.adrianovictorn.syshelp.dtos.ListCallDTO;
import io.github.adrianovictorn.syshelp.dtos.UpdateCallDTO;
import io.github.adrianovictorn.syshelp.dtos.ViewCallDTO;
import io.github.adrianovictorn.syshelp.service.CallService;

@RestController
@CrossOrigin(origins = "https://syshelp.ddns.net")
@RequestMapping("api/chamado")
public class CallController {

    private final CallService service;

    public CallController(CallService service) {
        this.service = service;
    }
    
    @PostMapping
    public ResponseEntity<ViewCallDTO> criarChamado(@RequestBody CreateCallDTO dto){
       ViewCallDTO view = service.criarChamado(dto);
       return new ResponseEntity<>(view,HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<ViewCallDTO> atualizarChamado (@RequestBody UpdateCallDTO dto, @PathVariable Long id){
        return ResponseEntity.ok(service.atualizarChamados(id, dto));
    }

    @GetMapping("meus/chamados")
    public ResponseEntity<List<ListCallDTO>> listarChamados(){
        List<ListCallDTO> listar = service.listarChamados();
       return ResponseEntity.ok(listar);
    }

    @GetMapping("{id}")
    public ResponseEntity<ViewCallDTO> buscarChamadoPorId(@PathVariable Long id) {
        ViewCallDTO chamado = service.buscarChamadoPorId(id);
        return ResponseEntity.ok(chamado);
    }

}
