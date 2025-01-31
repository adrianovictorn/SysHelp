package io.github.adrianovictorn.syshelp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginViewController {

    @GetMapping("/login")
    public String paginaLogin(){
        
        return "login";
    }

    @GetMapping("meus-chamados")
    public String paginaCadastro(){
        return "minha-lista-chamados";
    }

    @GetMapping("/chamados")
    public String indexPublico(){
        return "index";
    }
    
}
