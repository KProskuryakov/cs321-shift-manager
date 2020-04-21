package edu.gmu.cs321.team3.shiftmanager.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("TimeAlign_HomePage");
        registry.addViewController("/login").setViewName("TimeAlign_SignIn");
        registry.addViewController("/contact").setViewName("TimeAlign_ContactUs");
    }

}