package com.order.process.infrastructure.config;

import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MustacheConfiguration {

    @Bean
    public MustacheViewResolver mustacheViewResolver() {
        MustacheViewResolver resolver = new MustacheViewResolver();
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".mustache");
        resolver.setCache(false);
        return resolver;
    }

}
