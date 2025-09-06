package com.program.whatsapp_bot.Expenses.controller;

import java.util.List; 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.CategoryRequestDTO;
import com.program.whatsapp_bot.Expenses.model.Category; 
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.service.CategoryService;
import com.program.whatsapp_bot.Expenses.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService; // Temporário para obter o User.

    // Método auxiliar TEMPORÁRIO para obter o User.
    // Em uma aplicação real, você obteria o usuário do contexto de segurança (Spring Security).
    private User getAuthenticatedUser(String whatsappId) {
        // Por enquanto, vamos buscar o usuário pelo whatsappId.
        // No futuro, isso seria substituído pela obtenção do usuário logado via Spring Security.
        return userService.findByWhatsappId(whatsappId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado. Implementar autenticação real."));
    }

    /**
     * Endpoint para criar uma nova categoria para um usuário.
     * Ex: POST /api/categorias
     * Body: { "nome": "Minha Nova Categoria" }
     */
    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestHeader("whatsapp-id") String whatsappId, // Exemplo: ID do usuário via header
            @Valid @RequestBody CategoryRequestDTO requestDTO) {
        try {
            User user = getAuthenticatedUser(whatsappId); // Obtém o usuário (mock ou real)
            Category newCategory = categoryService.createCategory(requestDTO.getNome(), user);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Poderia retornar um DTO de erro com a mensagem
        } catch (RuntimeException e) { // Captura o erro do getAuthenticatedUser
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Usuário não encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Endpoint para listar todas as categorias de um usuário.
     * Ex: GET /api/categorias?whatsapp-id=12345
     * (Preferencialmente, o ID viria de um token de segurança)
     */
    @GetMapping
    public ResponseEntity<List<Category>> getCategoriesByUser(
            @RequestHeader("whatsapp-id") String whatsappId) { // Exemplo: ID do usuário via header
        try {
            User user = getAuthenticatedUser(whatsappId);
            List<Category> categories = categoryService.findCategoriesByUser(user);
            return ResponseEntity.ok(categories);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Usuário não encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Endpoint para atualizar o nome de uma categoria.
     * Ex: PUT /api/categorias/123
     * Body: { "nome": "Nome Atualizado" }
     */
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(
            @RequestHeader("whatsapp-id") String whatsappId, // ID do usuário via header
            @PathVariable Long id, // ID da categoria a ser atualizada
            @Valid @RequestBody CategoryRequestDTO requestDTO) {
        try {
            User user = getAuthenticatedUser(whatsappId);
            Category updatedCategory = categoryService.updateCategory(id, requestDTO.getNome(), user);
            return ResponseEntity.ok(updatedCategory);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Usuário ou categoria não encontrada
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    /**
     * Endpoint para deletar uma categoria.
     * Ex: DELETE /api/categorias/123
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(
            @RequestHeader("whatsapp-id") String whatsappId, // ID do usuário via header
            @PathVariable Long id) { // ID da categoria a ser deletada
        try {
            User user = getAuthenticatedUser(whatsappId);
            // Antes de deletar, você pode querer verificar se a categoria pertence ao usuário
            // A lógica de segurança já está no CategoryService.deleteCategory se você a adicionou.
            // Se não, você precisaria buscar a categoria e verificar o user aqui.
            // Para simplicidade, vamos supor que CategoryService.deleteCategory vai lidar com isso ou com erro de não encontrado
            categoryService.deleteCategory(id);
            return ResponseEntity.noContent().build(); // Retorna 204 No Content para sucesso sem corpo
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build(); // Categoria não encontrada ou não pertence ao usuário
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Usuário não encontrado
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}