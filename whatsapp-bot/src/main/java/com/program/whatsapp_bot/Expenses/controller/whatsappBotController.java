package com.program.whatsapp_bot.Expenses.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.MessageDTO;
import com.program.whatsapp_bot.Expenses.dto.Request.CreateUserRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Request.whatsappBotRequestDTO;
import com.program.whatsapp_bot.Expenses.service.UserService;
import com.program.whatsapp_bot.Expenses.service.whatsappBotService;

@RestController
@RequestMapping("/webhook")
public class whatsappBotController {
    @Autowired
    private whatsappBotService botService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<Void> receiveMessage(@RequestBody whatsappBotRequestDTO request) {
        try {
            var changes = request.getEntry().get(0).getChanges().get(0);
            var value = changes.getValue();

            if (value.getMessages() != null && !value.getMessages().isEmpty()) {
                var message = value.getMessages().get(0);
                
                String waId = message.getFrom();
                String mensagemRecebida = message.getText().getBody();
                
                String nomeDoContato = value.getContacts().get(0).getProfile().getName();

                System.out.println("Mensagem recebida de " + waId + " (" + nomeDoContato + "): " + mensagemRecebida);

                CreateUserRequestDTO user = new CreateUserRequestDTO();
                user.setWaId(waId);
                user.setNome(nomeDoContato);

                userService.cadastrarOuBuscar(user.getWaId(),user.getNome());

                botService.processarMensagem(waId, mensagemRecebida);
            }
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Erro ao processar webhook: " + e.getMessage());
            return ResponseEntity.ok().build();
        }
    }

}