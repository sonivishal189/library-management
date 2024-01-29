package vishal.test.library.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Library Management System")
                        .description("APIs to manage books, patron, and borrowing/returning of books")
                        .contact(new Contact()
                                .name("Vishal Soni")
                                .url("https://www.linkedin.com/in/vishal-soni-07/")
                        )
                ).externalDocs(new ExternalDocumentation()
                        .description("GitHub")
                        .url("https://github.com/sonivishal189/library-management-system")
                );
    }
}
