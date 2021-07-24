package com.jvimora.bia.energy.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springdoc.core.SwaggerUiConfigParameters;
import org.springdoc.core.SwaggerUiOAuthProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
public class OpenApiConfig {


    @Bean
    public OpenAPI customOpenAPI() {

        final String SECURITY_SCHEME_NAME = "bearerAuth";

        return new OpenAPI()
                .info(new Info()
                        .title("jvimora bia")
                        .description("Demo registro lecturas")
                        .contact(new Contact()
                                .name("jvimora")
                                .url("https://www.linkedin.com/in/fjmoradiaz")
                                .email("fjaviermoradiaz@gmail.com")));
    }


}
