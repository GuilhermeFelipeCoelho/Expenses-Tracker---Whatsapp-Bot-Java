package com.program.whatsapp_bot.Expenses.controller;

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

import com.program.whatsapp_bot.Expenses.dto.Request.CategoriaRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.CategoriaResponseDTO;
import com.program.whatsapp_bot.Expenses.model.Categoria;
import com.program.whatsapp_bot.Expenses.service.CategoriaService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@RequestBody CategoriaRequestDTO requestDTO) {
        Categoria novaCategoria = categoriaService.salvar(requestDTO);
        CategoriaResponseDTO responseDTO = new CategoriaResponseDTO(novaCategoria);
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> listarCategorias() {
        List<Categoria> categorias = categoriaService.buscarTodas();
        List<CategoriaResponseDTO> responseDTOs = categorias.stream()
            .map(CategoriaResponseDTO::new)
            .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> buscarPorId(@PathVariable Long id) {
        Optional<Categoria> categoriaOptional = categoriaService.buscarPorId(id);
        
        if (categoriaOptional.isPresent()) {
            CategoriaResponseDTO responseDTO = new CategoriaResponseDTO(categoriaOptional.get());
            return ResponseEntity.ok(responseDTO);
        }
        
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> atualizarCategoria(@PathVariable Long id, @RequestBody CategoriaRequestDTO requestDTO) {
        Categoria categoriaAtualizada = categoriaService.atualizar(id, requestDTO);

        if (categoriaAtualizada != null) {
            CategoriaResponseDTO responseDTO = new CategoriaResponseDTO(categoriaAtualizada);
            return ResponseEntity.ok(responseDTO);
        }

        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        boolean deletada = categoriaService.deletar(id);
        
        if (deletada) {
            return ResponseEntity.noContent().build();
        }
        
        return ResponseEntity.notFound().build();
    }
}