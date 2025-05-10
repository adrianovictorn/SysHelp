package io.github.adrianovictorn.syshelp.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class RelatorioService {

    public void gerarRelatorio(HttpServletResponse response, String nomeArquivo, Map<String, Object> parametros, Optional<JRBeanCollectionDataSource> dados) {
        try {
            // Caminho do relatório dentro de resources/reports/
            InputStream reportStream = getClass().getResourceAsStream("/reports/relatorio_chamados.jrxml");

            // Compila o relatório (se necessário)
            JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

            // Cria o objeto JasperPrint
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dados.orElse(null));

            // Configuração para gerar o PDF na resposta HTTP
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline; filename=relatorio_chamados.pdf");

            // Exporta o relatório para o navegador
            JasperExportManager.exportReportToPdfStream(jasperPrint, response.getOutputStream());

        } catch (JRException | IOException e) {
            throw new RuntimeException("Erro ao gerar relatório: " + e.getMessage(), e);
        }
    }
}
