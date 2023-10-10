package com.example.patient.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class ApiFilter implements Filter {

    public static final Logger logger= LoggerFactory.getLogger(ApiFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest=(HttpServletRequest) request;
        Enumeration<String> headerNames= httpServletRequest.getHeaderNames();

        if(headerNames!=null){
            while(headerNames.hasMoreElements()){
                String headerName=headerNames.nextElement();
                String headerValue=httpServletRequest.getHeader(headerName);
                logger.debug("HeaderName : "+headerName+", HeaderValue : "+headerValue);
            }
        }
        try{
            //Setup MDC Data
            MDC.put("userid ",request.getParameter("userId")==null?"System":request.getParameter("userId"));
            logger.info("userId : "+MDC.get("userId"));
            String attributes= httpServletRequest.getAttributeNames().toString();
            String url=httpServletRequest.getRequestURI();
            logger.debug("Url : "+url+ ", Attributes : "+attributes);
            chain.doFilter(request,response);
        }finally {
            //cleanup data
            MDC.clear();
            logger.info("Cleaned up MDC Data");
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
