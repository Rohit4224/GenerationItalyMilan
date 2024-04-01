package com.generation.scuolarest.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.scuolarest.dto.LoginStatus;
import com.generation.scuolarest.entities.Dirigente;
import com.generation.scuolarest.entities.Docente;
import com.generation.scuolarest.entities.Persona;
import com.generation.scuolarest.entities.Studente;
import com.generation.scuolarest.services.LoginService;

@RestController
@RequestMapping("api/login")
public class LoginRestController {
    
    @Autowired
    private LoginService loginService;

    @PostMapping("/signin")
    public LoginStatus signin(@RequestBody Map<String, String> body){
        Persona p = loginService.findUser(body.get("username"), body.get("password"));
        LoginStatus ls = new LoginStatus();
        ls.setToken("NONE", false, -1);
        
        if(p instanceof Studente){
            System.out.println("LOGIN: STUDENTE");
            ls.setToken("STUDENTE", true, p.getId());
            return ls;
        }
        else if(p instanceof Docente){
            System.out.println("LOGIN: DOCENTE");
            ls.setToken("DOCENTE", true, p.getId());
            return ls;
        }
        else if(p instanceof Dirigente){
            System.out.println("LOGIN: DIRIGENTE");
            ls.setToken("DIRIGENTE", true, p.getId());
            return ls;
        }
        else{
            return ls;
        }
    }

}