package com.program.whatsapp_bot.Expenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.repository.UsuarioRepository;

import java.util.Optional;

import com.program.whatsapp_bot.Expenses.dto.Request.CreateUserRequestDTO;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<User> findByWaId(String waId) {
        return usuarioRepository.findByWaId(waId);
    }

     public Optional<User> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    public User cadastrarOuBuscar(String waId, String nome) {
        Optional<User> usuarioExistente = findByWaId(waId);
        
        if (usuarioExistente.isPresent()) {
            return usuarioExistente.get();
        }
        
        User novoUsuario = new User();
        novoUsuario.setWaId(waId);
        novoUsuario.setNome(nome);
        return usuarioRepository.save(novoUsuario);
    }

     public User atualizar(Long id, CreateUserRequestDTO request) {
        Optional<User> usuarioExistente = usuarioRepository.findById(id);
        
        if (usuarioExistente.isPresent()) {
            User usuario = usuarioExistente.get();
            usuario.setNome(request.getNome());
            usuario.setWaId(request.getWaId());
            return usuarioRepository.save(usuario);
        }
        
        return null;
    }
}