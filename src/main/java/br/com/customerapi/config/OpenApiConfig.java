package br.com.customerapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI config() {
        return new OpenAPI()
                .info(getInfo());
    }

    private Info getInfo() {
        return new Info()
                .title("Customer-api")
                .description("Customers api")
                .version("1.0")
                .contact(getContact());
    }

    private Contact getContact() {
        return new Contact()
                .email("renato.alexsander13@gmail.com")
                .name("Renato Alexsander");
    }
}
