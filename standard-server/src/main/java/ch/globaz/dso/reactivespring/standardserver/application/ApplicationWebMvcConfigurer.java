package ch.globaz.dso.reactivespring.standardserver.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class ApplicationWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private HelloRequestInterceptor helloRequestInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(helloRequestInterceptor)
                .addPathPatterns("/**/hello/**/");
    }
}
