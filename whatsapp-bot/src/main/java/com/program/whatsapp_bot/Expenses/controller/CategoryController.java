package com.program.whatsapp_bot.Expenses.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.program.whatsapp_bot.Expenses.dto.Request.CategoryRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.CategoryResponseDTO ;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.service.CategoryService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryResponseDTO> criarCategoria(@RequestBody CategoryRequestDTO requestDTO) {
        Category novaCategoria = categoryService.salvar(requestDTO);
        CategoryResponseDTO responseDTO = new CategoryResponseDTO(novaCategoria);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponseDTO>> listarCategorias() {
        List<Category> categorias = categoryService.buscarTodas();
        List<CategoryResponseDTO> responseDTOs = categorias.stream()
            .map(CategoryResponseDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Category> categoriaOptional = categoryService.buscarPorId(id);
        
        if (categoriaOptional.isPresent()) {
            CategoryResponseDTO responseDTO = new CategoryResponseDTO(categoriaOptional.get());
            return ResponseEntity.ok(responseDTO);
        }
        
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoryRequestDTO requestDTO) {
        Category categoriaAtualizada = categoryService.atualizar(id, requestDTO);


        if (categoriaAtualizada != null) {
            CategoryResponseDTO responseDTO = new CategoryResponseDTO(categoriaAtualizada);
            return ResponseEntity.ok(responseDTO);
        }

        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        boolean deletada = categoryService.deletar(id);

        
        if (deletada) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}