package ru.decathlon.swagger.example.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfiguration {

    @Bean
    public OpenAPI exampleOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Example with cats.")
                .version("9.9.9")
                .description("Swagger example with Cats. Meow-meow. (´｡• ω •｡)")
                .license(new License().name("Animal Passport 1.0")
                        .url("https://en.wikipedia.org/wiki/Cat")));
    }
}