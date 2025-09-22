package com.program.whatsapp_bot.Expenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.program.whatsapp_bot.Expenses.dto.MessageDTO;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.service.UserService;

@RestController
@RequestMapping("/webhook")
public class whatsapp_botController {

    @Autowired
    private UserService usuarioService;

    @PostMapping
    public ResponseEntity<Void> receiveMessage(@RequestBody MessageDTO message) {
        if (message.getText() != null && message.getText().startsWith("cadastrar")) {
            String[] partes = message.getText().split(" ", 2);
            if (partes.length > 1) {
                String nomeUsuario = partes[1].trim();
                String waId = message.getFrom();
                
                User usuario = usuarioService.cadastrarOuBuscar(waId, nomeUsuario);
                
                System.out.println("Processada mensagem de: " + usuario.getNome());
            }
        }
        return ResponseEntity.ok().build();
    }
}