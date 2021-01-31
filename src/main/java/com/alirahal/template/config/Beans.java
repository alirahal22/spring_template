package com.alirahal.template.config;

import com.alirahal.template.services.BrandsService;
import com.alirahal.template.services.ProductsService;
import com.alirahal.template.services.ValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class Beans {

    @Bean
    @Primary
    public BrandsService getBrandsService() {
        return new BrandsService();
    }

    @Bean
    public ProductsService getProductService() {
        return new ProductsService();
    }

    @Bean
    public ValidationService getValidationService() {
        return new ValidationService();
    }

}
