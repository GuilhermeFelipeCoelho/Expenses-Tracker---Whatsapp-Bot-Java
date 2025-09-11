package com.program.whatsapp_bot.Expenses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.program.whatsapp_bot.Expenses.dto.Request.ExpenseRequestDTO;
import com.program.whatsapp_bot.Expenses.model.Expense;
import com.program.whatsapp_bot.Expenses.repository.ExpenseRepository;

@Service
public class ExpenseService {
    
    @Autowired
    private ExpenseRepository expenseRespository;

    public Expense salvar(ExpenseRequestDTO requestDTO){
        Expense expense = new Expense();
        expense.setCategory(requestDTO.getCategory());
        expense.setDescricao(requestDTO.getDescricao());
        expense.setValor(requestDTO.getValor());
        expense.setTipo(requestDTO.getTipo());
        expense.setUser(requestDTO.getUser());
        return expenseRespository.save(expense);
    }

}



// @Service
// public class CategoryService {


//     public List<Category> buscarTodas() {
//         return categoriaRepository.findAll();
//     }

//     public Optional<Category> buscarPorId(Long id) {
//         return categoriaRepository.findById(id);
//     }
    
//     public Optional<Category> buscarPorNome(String nome) {
//         return categoriaRepository.findByNome(nome);
//     }

//     public Category atualizar(Long id, CategoryRequestDTO requestDTO) {
//         Optional<Category> categoriaOptional = categoriaRepository.findById(id);
        
//         if (categoriaOptional.isPresent()) {
//             Category categoria = categoriaOptional.get();
//             categoria.setNome(requestDTO.getNome());
//             return categoriaRepository.save(categoria);
//         }
        
//         return null;
//     }
    
//     public boolean deletar(Long id) {
//         if (categoriaRepository.existsById(id)) {
//             categoriaRepository.deleteById(id);
//             return true;
//         }
//         return false;
//     }
// }