package com.program.whatsapp_bot.Expenses.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.MessageDTO;

@RestController
@RequestMapping("/webhook")
public class whatsappBotController {

    @PostMapping
    public ResponseEntity<Void> receiveMessage(@RequestBody MessageDTO message) {
        return ResponseEntity.ok().build();
    }
}
