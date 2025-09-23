package com.program.whatsapp_bot.Expenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.program.whatsapp_bot.Expenses.dto.Request.CategoryRequestDTO;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoriaRepository;

    public Category salvar(CategoryRequestDTO requestDTO) {
        Category categoria = new Category();
        categoria.setNome(requestDTO.getNome());
        return categoriaRepository.save(categoria);
    }

    public List<Category> buscarTodas() {
        return categoriaRepository.findAll();
    }

    public Optional<Category> buscarPorId(Long id) {
        return categoriaRepository.findById(id);
    }

    public Optional<Category> buscarPorNome(String nome) {
        return categoriaRepository.findByNome(nome);
    }

    public Category atualizar(Long id, CategoryRequestDTO requestDTO) {
        Optional<Category> categoriaOptional = categoriaRepository.findById(id);

        if (categoriaOptional.isPresent()) {
            Category categoria = categoriaOptional.get();
            categoria.setNome(requestDTO.getNome());
            return categoriaRepository.save(categoria);
        }

        return null;
    }

    public boolean deletar(Long id) {
        Category category = categoriaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transação não encontrada"));

        if (category != null) {
            category.setAtivo(false);
            categoriaRepository.save(category);
            return true;
        }
        return false;
    }
}