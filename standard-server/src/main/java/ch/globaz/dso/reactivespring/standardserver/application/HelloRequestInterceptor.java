package ch.globaz.dso.reactivespring.standardserver.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class HelloRequestInterceptor extends HandlerInterceptorAdapter {

    private final Logger LOGGER = LoggerFactory.getLogger(HelloRequestInterceptor.class);
    @Override
    public boolean preHandle(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler) {

        LOGGER.debug("preHandle: {}",request.getPathInfo());
        return true;
    }

    @Override
    public void afterCompletion(
            HttpServletRequest request,
            HttpServletResponse response,
            Object handler,
            Exception ex) {
        LOGGER.debug("afterCompletion: {}",request.getPathInfo());
        //
    }
}
