package org.aws.demo.data.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomFilter implements Filter {

    Logger logger = LoggerFactory.getLogger(CustomFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if(((HttpServletRequest)request).getRequestURI().startsWith("/api"))
        logData((HttpServletRequest) request, (HttpServletResponse) response, chain);
        chain.doFilter(request,response);
    }

    private void logData(HttpServletRequest request, HttpServletResponse response, FilterChain chain) {
        System.out.println("requestURI: "+  request.getHeader("correlation-id"));
    }

    @Override
    public void destroy() {

    }
}
