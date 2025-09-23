package com.program.whatsapp_bot.Expenses.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Locale.Category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.program.whatsapp_bot.Expenses.dto.Request.CategoryRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Request.CreateUserRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Request.ExpenseRequestDTO;
import com.program.whatsapp_bot.Expenses.dto.Response.UserResponseDTO;
import com.program.whatsapp_bot.Expenses.model.User;
import com.program.whatsapp_bot.Expenses.repository.UserRepository;

@Service
public class whatsappBotService {

    @Value("${whatsapp.api.url}")
    private String apiUrl;

    @Value("${whatsapp.api.phone-number-id}")
    private String phoneNumberId;

    @Value("${whatsapp.api.access-token}")
    private String accessToken;

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;

    public void processarMensagem(String waId, String mensagemRecebida) {
        String mensagemNormalizada = mensagemRecebida.toLowerCase().trim();

        if (mensagemNormalizada.startsWith("categoria ")) {
            try {
                CategoryRequestDTO nomeCategoria = new CategoryRequestDTO();
                nomeCategoria.setNome(mensagemNormalizada.substring("categoria ".length()).trim());

                categoryService.salvar(nomeCategoria);
                /* Falta Implementar com API do Whats para testar essa parte*/
                // enviarMensagemTexto(waId, "Categoria '" + nomeCategoria + "' cadastrada com sucesso!");
            } catch (Exception e) {
                /* Falta Implementar com API do Whats para testar essa parte*/
                // enviarMensagemTexto(waId, "Ops! Não consegui cadastrar a categoria. Por favor, tente novamente.");
            }

        } else if (mensagemNormalizada.startsWith("expense ")) {
            try {
                String[] partes = mensagemNormalizada.substring("expense ".length()).trim().split(" ", 3);

                
                if (partes.length < 3) {
                    /* Falta Implementar com API do Whats para testar essa parte*/
                    //enviarMensagemTexto(waId,"Ops! O formato da despesa está incorreto. Tente 'expense [valor] [categoria] [descrição]'.");
                    return;
                }


                BigDecimal valor = new BigDecimal(partes[0].replace(",", ".")); 
                String descricao = partes[2];
                Optional<User> optionalUsuario = userService.findByWaId(waId);
                
                User usuario = optionalUsuario.orElseThrow(() -> new RuntimeException("Usuário não encontrado para o waId: " + waId));
                Optional<com.program.whatsapp_bot.Expenses.model.Category> optionalCategory = categoryService.buscarPorNome(partes[1]);
                com.program.whatsapp_bot.Expenses.model.Category categoria = optionalCategory.orElseThrow(() -> new RuntimeException("Categoria não Encontrada: " + partes[1]));

                System.out.println(usuario.getId());
                System.out.println(valor);
                System.out.println(descricao);



                ExpenseRequestDTO expenseRequestDTO = new ExpenseRequestDTO();
                expenseRequestDTO.setValor(valor);
                expenseRequestDTO.setDescricao(descricao);
                expenseRequestDTO.setUsuario(usuario.getId()); 
                expenseRequestDTO.setCategoria(categoria.getId());

                expenseService.salvar(expenseRequestDTO);

                /* Falta Implementar com API do Whats para testar essa parte*/
                //enviarMensagemTexto(waId, "Despesa '" + descricao + "' de R$" + String.format("%.2f", valor)+ " cadastrada com sucesso!");

            } catch (NumberFormatException e) {
                /* Falta Implementar com API do Whats para testar essa parte*/
                //enviarMensagemTexto(waId, "O valor da despesa deve ser um número. Por favor, tente novamente.");
            } catch (Exception e) {
                /* Falta Implementar com API do Whats para testar essa parte*/
                //enviarMensagemTexto(waId, "Ocorreu um erro ao processar sua despesa. Por favor, tente novamente.");
                System.err.println("Erro ao processar despesa: " + e.getMessage());
            }

        } else {
            // enviarMensagemTexto(waId, "Desculpe, não entendi sua solicitação. Use 'categoria [nome]' ou 'expense [valor] [nome]'.");
            System.out.println("erro");
        }
    }

    public void enviarMensagemTexto(String numeroDestino, String mensagem) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        Map<String, Object> textBody = new HashMap<>();
        textBody.put("body", mensagem);

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("messaging_product", "whatsapp");
        requestBody.put("recipient_type", "individual");
        requestBody.put("to", numeroDestino);
        requestBody.put("type", "text");
        requestBody.put("text", textBody);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);
        String url = apiUrl + "/" + phoneNumberId + "/messages";

        try {
            restTemplate.postForObject(url, entity, String.class);
            System.out.println("Mensagem enviada com sucesso para " + numeroDestino);
        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
        }
    }
}