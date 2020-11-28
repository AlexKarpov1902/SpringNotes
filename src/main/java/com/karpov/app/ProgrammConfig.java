/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.karpov.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

/**
 *
 * @author Alex_2
 */
@Configuration
public class ProgrammConfig {
    @Bean
    public IDialect conditionalCommentDialect() {
        return new Java8TimeDialect();
    }
}

