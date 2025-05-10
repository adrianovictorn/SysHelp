package io.github.adrianovictorn.syshelp.controller;

import io.github.adrianovictorn.syshelp.service.RelatorioService;
import io.github.adrianovictorn.syshelp.entity.Call;
import io.github.adrianovictorn.syshelp.repository.CallRepository;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/api/chamado/relatorio")
public class RelatorioController {

    @Autowired
    private RelatorioService relatorioService;

    @Autowired
    private CallRepository chamadoRepository;

    @GetMapping
    public void gerarRelatorio(HttpServletResponse response) {
        // Busca os dados no banco
        List<Call> chamados = chamadoRepository.findAll();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(chamados);

        // Parâmetros para o relatório
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("TITULO", "Relatório de Chamados");

        // Gera o relatório
        relatorioService.gerarRelatorio(response, "relatorio_chamados", parametros, Optional.of(dataSource));
    }
}
