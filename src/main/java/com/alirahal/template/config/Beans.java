package com.alirahal.template.config;

import com.alirahal.template.services.ProductService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public ProductService getProductService() {
        return new ProductService();
    }
}
