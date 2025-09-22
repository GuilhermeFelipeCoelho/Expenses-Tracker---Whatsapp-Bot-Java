package com.program.whatsapp_bot.Expenses.service;

import org.springframework.http.ResponseEntity;

import com.program.whatsapp_bot.Expenses.dto.Request.whatsappBotRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.whatsappBotResponseDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

public class whatsappBotService {

    public ResponseEntity<whatsappBotResponseDTO> receiveMessage(@RequestBody whatsappBotRequestDTO message) {
        return ResponseEntity.noContent().build();
    }
}
