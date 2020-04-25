package com.company.Newsline.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.ServletWebArgumentResolverAdapter;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

//    @Override
//    public void addArgumentResolvers(List   <HandlerMethodArgumentResolver> argumentResolvers) {
////        PageableArgumentResolver resolver = new PageableArgumentResolver();
//        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
//        resolver.setFallbackPageable( PageRequest.of(1, 5));
//        argumentResolvers.add(new ServletWebArgumentResolverAdapter(resolver));
//
////        PageableHandlerMethodArgumentResolver resolver = new PageableHandlerMethodArgumentResolver();
////        resolver.setPageParameterName("page.page");
////        resolver.setSizeParameterName("page.size");
////        resolver.setOneIndexedParameters(true);
////        argumentResolvers.add(resolver);
//    }


}
