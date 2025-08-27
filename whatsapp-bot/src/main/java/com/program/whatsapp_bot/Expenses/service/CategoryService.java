package com.program.whatsapp_bot.Expenses.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.repository.CategoryRepository;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public Category createCategory(String nome, User user) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da categoria não pode ser vazio.");
        }
        if (user == null) {
            throw new IllegalArgumentException("Uma categoria deve estar associada a um usuário.");
        }
        
        Optional<Category> existingCategory = categoryRepository.findByNomeAndUser(nome, user);
        if (existingCategory.isPresent()) {
            throw new IllegalArgumentException("Categoria '" + nome + "' já existe para o usuário " + user.getNome() + ".");
        }
        Category category = new Category();
        category.setNome(nome.trim());
        category.setUser(user);
        return categoryRepository.save(category);
    }
    
    public List<Category> findCategoriesByUser(User user) {
        return categoryRepository.findByUser(user);
    }
    
    public Optional<Category> findCategoryByNomeAndUser(String nome, User user) {
        return categoryRepository.findByNomeAndUser(nome, user);
    }
    
    @Transactional
    public void deleteCategory(Long categoryId) {
        if (!categoryRepository.existsById(categoryId)) {
            throw new IllegalArgumentException("Categoria com ID " + categoryId + " não encontrada para exclusão.");
        }
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public Category updateCategory(Long categoryId, String novoNome, User user) { // <<< ASSINATURA CORRETA
        if (novoNome == null || novoNome.trim().isEmpty()) {
            throw new IllegalArgumentException("O novo nome da categoria não pode ser vazio.");
        }

        Category categoryToUpdate = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new IllegalArgumentException("Categoria com ID " + categoryId + " não encontrada."));

        if (!categoryToUpdate.getUser().getWhatsappId().equals(user.getWhatsappId())) {
            throw new IllegalArgumentException("Você não tem permissão para alterar esta categoria.");
        }

        Optional<Category> existingCategoryWithNewName = categoryRepository.findByNomeAndUser(novoNome.trim(), user);
        if (existingCategoryWithNewName.isPresent() && !existingCategoryWithNewName.get().getId().equals(categoryId)) {
            throw new IllegalArgumentException("Já existe uma categoria com o nome '" + novoNome + "' para este usuário.");
        }

        categoryToUpdate.setNome(novoNome.trim());
        return categoryRepository.save(categoryToUpdate);
    }

}
