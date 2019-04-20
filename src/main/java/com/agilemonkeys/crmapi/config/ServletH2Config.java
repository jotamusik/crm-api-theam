package com.agilemonkeys.crmapi.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServletH2Config {

    @Bean
    ServletRegistrationBean<WebServlet> h2ServletRegistration() {
        ServletRegistrationBean<WebServlet> registrationBean = new ServletRegistrationBean<WebServlet>(
                new WebServlet());
        registrationBean.addUrlMappings("/h2_console/*");
        return registrationBean;
    }
}
