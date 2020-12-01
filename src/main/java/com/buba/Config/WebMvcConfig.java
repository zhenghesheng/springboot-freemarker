package com.buba.Config;

import com.buba.Component.LoginInterceptor;
import com.buba.Component.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean(name="localeResolver")
    public LocaleResolver LocaleResolver(){
        return new MyLocaleResolver();
    }

    @Bean
    public HandlerInterceptor getMyInterceptor(){
        return new LoginInterceptor();
    }

    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截的请求，并排除几个不拦截的请求
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/test/login", "/test/tologin","/images/**","/scripts/**","/style/**");
    }



}
