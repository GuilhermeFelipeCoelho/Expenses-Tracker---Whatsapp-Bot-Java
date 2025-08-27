package com.program.whatsapp_bot.Expenses.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.program.whatsapp_bot.Expenses.dto.CreateUserRequestDTO;
import com.program.whatsapp_bot.Expenses.model.Category;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.repository.CategoryRepository;
import com.program.whatsapp_bot.Expenses.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository; // Injeção de dependência do CategoryRepository

    // Lista de categorias padrão (você pode carregar isso de um arquivo de configuração ou do banco de dados)
    private static final List<String> DEFAULT_CATEGORIES = Arrays.asList(
        "🛒Mercado", "💊Farmácia", "🎡Lazer", "Alimentação", "Transporte", "Moradia", "Lazer", "Saúde", "Educação", "Salário", "Outros");
    public Optional<User> findByWhatsappId(String whatsappId) {
        return userRepository.findByWhatsappId(whatsappId);
    }

    @Transactional // Garante que todas as operações dentro deste método sejam uma única transação de banco de dados
    public User cadastrarUsuario(CreateUserRequestDTO requestDTO) {
        // 1. Validações básicas (já implementadas)
        if (requestDTO.getWhatsappId() == null || requestDTO.getWhatsappId().isEmpty()) {
            throw new IllegalArgumentException("ID do WhatsApp não pode ser vazio.");
        }
        if (requestDTO.getNome() == null || requestDTO.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do usuário não pode ser vazio.");
        }

        // 2. Verifica se o usuário já existe
        Optional<User> existingUser = userRepository.findByWhatsappId(requestDTO.getWhatsappId());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("Usuário com este WhatsApp ID já existe.");
        }

        // 3. Cria a instância do seu Model User
        User newUser = new User();
        newUser.setWhatsappId(requestDTO.getWhatsappId());
        newUser.setNome(requestDTO.getNome());

        // 4. Salva o usuário primeiro para obter um ID (se o whatsappId não fosse a PK)
        // Como whatsappId é a PK, podemos salvar depois de adicionar as categorias,
        // mas é uma boa prática salvar a entidade "pai" primeiro se o ID for gerado.
        // No seu caso, como whatsappId é a PK, não há problema em adicionar as categorias e depois salvar o user.

        // 5. Lógica para adicionar categorias padrão
        if (requestDTO.isUsarCategoriasPadrao()) {
            for (String categoryName : DEFAULT_CATEGORIES) {
                // Verifica se a categoria já existe para este usuário (improvável para padrão, mas boa prática)
                Optional<Category> existingCategory = categoryRepository.findByNomeAndUser(categoryName, newUser);
                if (existingCategory.isEmpty()) { // Se não existir, cria e associa
                    Category category = new Category();
                    newUser.addCategory(category); // Adiciona a categoria à lista do usuário e seta o user na categoria
                }
            }
        }

        // 6. Lógica para adicionar categorias personalizadas
        if (requestDTO.getCategoriasPersonalizadas() != null && !requestDTO.getCategoriasPersonalizadas().isEmpty()) {
            for (String customCategoryName : requestDTO.getCategoriasPersonalizadas()) {
                // Normaliza o nome da categoria para evitar duplicatas por case
                String normalizedName = customCategoryName.trim();
                if (normalizedName.isEmpty()) continue; // Pula nomes vazios

                // Verifica se a categoria personalizada já existe para este usuário
                Optional<Category> existingCustomCategory = categoryRepository.findByNomeAndUser(normalizedName, newUser);
                if (existingCustomCategory.isEmpty()) { // Se não existir, cria e associa
                    Category category = new Category();
                    newUser.addCategory(category); // Adiciona a categoria à lista do usuário e seta o user na categoria
                }
            }
        }

        // 7. Salva o usuário (e suas categorias associadas devido ao CascadeType.ALL)
        return userRepository.save(newUser);
    }

    // Outros métodos de serviço podem ser adicionados aqui
}