package com.program.whatsapp_bot.Expenses.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI apiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API WhatsApp Expense Tracker")
                        .description("API RESTful para registrar e listar despesas via WhatsApp")
                        .version("0.0.1"));
    }
}
