package com.alirahal.template.config;

import com.alirahal.template.services.StudentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Beans {

    @Bean
    public StudentService getStudentService() {
        return new StudentService();
    }

}
