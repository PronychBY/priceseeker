package com.senla.pronych.hibernate.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.senla.pronych.hibernate.security.WebSecurityConfig;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class < ? > [] getRootConfigClasses() {
        return new Class[] {
            AppContext.class,
            WebSecurityConfig.class            
        };
    }

    @Override
    protected Class < ? > [] getServletConfigClasses() {
        return new Class[] {
            WebMvcConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
            "/"
        };
    }
}