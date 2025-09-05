package com.program.whatsapp_bot.Expenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.program.whatsapp_bot.Expenses.dto.Request.CategoriaRequestDTO;
import com.program.whatsapp_bot.Expenses.model.Categoria;
import com.program.whatsapp_bot.Expenses.repository.CategoriaRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria salvar(CategoriaRequestDTO requestDTO) {
        Categoria categoria = new Categoria();
        categoria.setNome(requestDTO.getNome());
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> buscarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }
    
    public Optional<Categoria> buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public Categoria atualizar(Long id, CategoriaRequestDTO requestDTO) {
        Optional<Categoria> categoriaOptional = categoriaRepository.findById(id);
        
        if (categoriaOptional.isPresent()) {
            Categoria categoria = categoriaOptional.get();
            categoria.setNome(requestDTO.getNome());
            return categoriaRepository.save(categoria);
        }
        
        return null;
    }
    
    public boolean deletar(Long id) {
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}