package com.suipayment.suiPays.securityConfig;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Service
//@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtService jwtService;
    @Autowired
    private ApplicationContext context;

    protected void doFilterInternal(HttpServletRequest request , HttpServletResponse response , FilterChain filterChain) throws ServletException , IOException{

        String path = request.getRequestURI();

        // Bypass JWT processing for signup endpoints
//        if (path.startsWith("/api/signup/register/") ||
//                path.startsWith("/api/signup/verify") ||
//                path.startsWith("/api/signup/username")) {
//            filterChain.doFilter(request, response);
//            return;
//        }
        String authHeader = request.getHeader("Authorization");
        String token = null ;
        String userName = null;

        if(authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7);
            userName = jwtService.extractUsername(token);
        }
        if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null){
            MyUserDetailsService userDetailsService = context.getBean(MyUserDetailsService.class);
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
//            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loyadUserByUsername(userName);
            if(jwtService.validateToken(token, userDetails)) { // we create  Method validate inside jwtService class

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails , null , userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));// this request here is the httpServet above
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }else{
                System.out.println("8.  Token not validated");
            }
        }
        filterChain.doFilter(request , response);
    }
}