package com.program.whatsapp_bot.Expenses.controller;

import org.springframework.beans.factory.annotation.Autowired; // Importe o DTO correto
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.CreateUserRequestDTO; // Importe o @RequestBody CORRETO
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.service.UserService;

@RestController
@RequestMapping("/api/usuarios")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/cadastrar")
    public ResponseEntity<User> cadastrar(@RequestBody CreateUserRequestDTO requestDTO) {
        try {
            User newUser = userService.cadastrarUsuario(requestDTO);

            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}