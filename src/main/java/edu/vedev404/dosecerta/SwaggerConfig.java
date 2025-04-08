package edu.vedev404.dosecerta;

import springfox.documentation.builders.RequestHandlerSelectors;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Component
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket (DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.dosecerta"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(new ApiInfoBuilder().title("DOSE SERTA API")
                        .description(("API para agerenciamento de medicamentos"))
                        .version("1.0")
                        .build());
    }
}
