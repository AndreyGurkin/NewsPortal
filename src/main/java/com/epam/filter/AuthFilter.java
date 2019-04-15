package com.epam.filter;

import com.epam.securityToken.TokenAuth;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import sun.plugin.liveconnect.SecurityContextHelper;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

@Component
public class AuthFilter implements Filter {
    String token;
    TokenAuth tokenAuth;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    
    }
    
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        for(Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                token=cookie.getValue();
            }
        }
        if (token==null){
            tokenAuth= new TokenAuth();
            tokenAuth.setAuthenticated(false);
        }
        else {
         tokenAuth=new TokenAuth(token);
            SecurityContextHolder.getContext().setAuthentication(tokenAuth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
    
    @Override
    public void destroy() {
    
    }
}
