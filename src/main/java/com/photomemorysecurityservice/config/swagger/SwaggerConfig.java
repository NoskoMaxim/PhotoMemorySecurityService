package com.photomemorysecurityservice.config.swagger;

import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket projectApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("photomemorysecurityservice"))
                .build()
                .apiInfo(this.projectApiDetails());
    }

    private ApiInfo projectApiDetails() {
        return new ApiInfoBuilder()
                .version("1.0.0")
                .title("Swagger Photo Memory Security Application")
                .description("This is the API documentation for the photo memory security application")
                .termsOfServiceUrl("http://swagger.io/terms/")
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}