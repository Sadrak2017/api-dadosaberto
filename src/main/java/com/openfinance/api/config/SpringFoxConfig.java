package com.openfinance.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {

  @Bean
  public Docket productApi() {
    Contact contact = new Contact(
        "Projeto no GitLab",
        "https://gitlab.com/developers-team-app/api",
        "empresa@smdcorps.com"
    );

    ApiInfo apiInfo = new ApiInfoBuilder()
        .title("API Open Finance")
        .description("Página para a disponibilidade de aplicações da back-end do aplicativo")
        .termsOfServiceUrl("Chicago Advisory Patterns")
        .contact(contact)
        .licenseUrl("empresa@s.com")
        .version("Alpha")
        .build();

    return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.mob.api"))
        .build();
  }

}
