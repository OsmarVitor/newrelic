//package com.example.newrelic.domain.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig extends WebMvcConfigurationSupport {
//
//  @Bean
//  public Docket api() {
//    return new Docket(DocumentationType.SWAGGER_2)
//        .select()
//        .apis(RequestHandlerSelectors.basePackage("com.challenge.hexalab"))
//        .paths(PathSelectors.ant("/**"))
//        .build()
//        .apiInfo(metaData());
//  }
//
//  private ApiInfo metaData() {
//    return new ApiInfoBuilder()
//        .title("Challenge HexaLab")
//        .description("Digital Wallet API.")
//        .version("“1.0.0”")
//        .build();
//  }
//
//  @Override
//  public void addViewControllers(ViewControllerRegistry registry) {
//    registry.addRedirectViewController("v2/api-docs", "/v2/api-docs");
//    registry.addRedirectViewController(
//        "swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
//    registry.addRedirectViewController(
//        "swagger-resources/configuration/security", "/swagger-resources/configuration/security");
//    registry.addRedirectViewController("swagger-resources", "/swagger-resources");
//  }
//
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry
//        .addResourceHandler("swagger-ui.html**")
//        .addResourceLocations("classpath:/META-INF/resources/swagger-ui.html");
//    registry
//        .addResourceHandler("webjars/**")
//        .addResourceLocations("classpath:/META-INF/resources/webjars/");
//  }
//}
