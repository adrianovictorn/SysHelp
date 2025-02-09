package io.github.adrianovictorn.syshelp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import io.github.adrianovictorn.syshelp.dtos.CreateCallDTO;
import io.github.adrianovictorn.syshelp.dtos.ListCallDTO;
import io.github.adrianovictorn.syshelp.dtos.UpdateCallDTO;
import io.github.adrianovictorn.syshelp.dtos.ViewCallDTO;
import io.github.adrianovictorn.syshelp.entity.Call;
import io.github.adrianovictorn.syshelp.repository.CallRepository;
import io.github.adrianovictorn.syshelp.service.CallService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("api/chamado")
public class CallController {

    private final CallService service;
    private final CallRepository callRepository;

    
    public CallController(CallService service, CallRepository callRepository) {
        this.service = service;
        this.callRepository = callRepository;
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
    
    

    // ... outros métodos permanecem iguais ...

    @GetMapping("/relatorio")
    public ModelAndView gerarRelatorio() {
        Map<String, Object> params = new HashMap<>();
        params.put("TITULO", "Relatório de Chamados SysHelp");
        
        List<Call> chamados = callRepository.findAll();
        JRDataSource dataSource = new JRBeanCollectionDataSource(chamados);

        ModelAndView model = new ModelAndView("relatorio_chamados"); 
        model.addObject("datasource", dataSource);
        model.addObject("format", "pdf"); 
        return model;
    }
}
        

