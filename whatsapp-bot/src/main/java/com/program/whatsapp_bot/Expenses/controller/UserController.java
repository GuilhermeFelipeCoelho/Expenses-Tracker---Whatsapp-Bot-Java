package com.program.whatsapp_bot.Expenses.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.Request.CreateUserRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.UserResponseDTO;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.service.UserService;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService usuarioService;

    @PostMapping("/cadastrar")
    public ResponseEntity<UserResponseDTO> cadastrar(@RequestBody CreateUserRequestDTO request) {
        User novoUsuario = new User();
        novoUsuario.setWaId(request.getWaId());
        novoUsuario.setNome(request.getNome());
        
        User usuarioSalvo = usuarioService.cadastrarOuBuscar(novoUsuario.getWaId(),novoUsuario.getNome()); 
        
        UserResponseDTO responseDTO = new UserResponseDTO(usuarioSalvo);
        
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }   

 
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<User> usuario = usuarioService.findById(id);

        if (usuario.isPresent()) {
            UserResponseDTO responseDTO = new UserResponseDTO(usuario.get());
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> atualizar(@PathVariable Long id, @RequestBody CreateUserRequestDTO request) {
        User usuarioAtualizado = usuarioService.atualizar(id, request);
        
        if (usuarioAtualizado != null) {
            UserResponseDTO responseDTO = new UserResponseDTO(usuarioAtualizado);
            return ResponseEntity.ok(responseDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}