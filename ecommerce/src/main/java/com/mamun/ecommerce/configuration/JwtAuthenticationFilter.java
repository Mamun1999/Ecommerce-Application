package com.mamun.ecommerce.configuration;

import com.mamun.ecommerce.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


      String jwttokenheader=  request.getHeader("Authorization");

      String username=null;
      String jwtToken=null;

      if(jwttokenheader!=null && jwttokenheader.startsWith("Bearer ")){

          jwtToken= jwttokenheader.substring(7);
          //get token without Bearer and a space of letter


         try {
             username= this.jwtUtil.getUsernameFromToken(jwtToken);
             //got username from token
         }catch (Exception e){
             e.printStackTrace();
         }

      }
      else{
          System.out.println("JWT Token does not start with bearer");
      }

      //find the user details using username for validation

       if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
           UserDetails userDetails= this.userDetailsService.loadUserByUsername(username);
           //got User details

           if(this.jwtUtil.validateToken(jwtToken,userDetails)){

               //token is valid
               //now we have to save this token for giving user access in api

              UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken= new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

              usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
              //save the authenticate user to security context holder
              SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);





           }
           else {
               System.out.println("Token is not valid");
           }
       }

       filterChain.doFilter(request,response);
    }
}
