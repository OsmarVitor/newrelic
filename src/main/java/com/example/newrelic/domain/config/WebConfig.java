//package com.example.newrelic.domain.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Import;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.EnableWebMvc;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
//
//@Configuration
//@EnableWebMvc
//@Import(SwaggerConfig.class)
//public class WebConfig extends WebMvcConfigurationSupport {
//
//  @Override
//  public void addCorsMappings(CorsRegistry registry) {
//    registry
//        .addMapping("/**")
//        .allowedMethods("GET", "PUT", "POST", "DELETE", "OPTIONS")
//        .allowedOrigins("*")
//        .allowedHeaders("*");
//  }
//
//  @Override
//  public void addResourceHandlers(ResourceHandlerRegistry registry) {
//    registry
//        .addResourceHandler("swagger-ui.html")
//        .addResourceLocations("classpath:/META-INF/resources/");
//
//    registry
//        .addResourceHandler("/webjars/**")
//        .addResourceLocations("classpath:/META-INF/resources/webjars/");
//  }
//}
