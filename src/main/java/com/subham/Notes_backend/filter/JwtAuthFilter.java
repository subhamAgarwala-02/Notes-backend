package com.subham.Notes_backend.filter;

import com.subham.Notes_backend.services.CustomUserDetailsService;
import com.subham.Notes_backend.services.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username = null;

        System.out.println("Incoming request URI: " + request.getRequestURI());

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
            System.out.println("Extracted Token: " + token);

            try {
                username = jwtService.extractUsername(token);
                System.out.println("Extracted Username: " + username);
            } catch (Exception e) {
                System.out.println("Exception while extracting username from token: " + e.getMessage());
            }
        } else {
            System.out.println("Authorization header is missing or doesn't start with Bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            System.out.println("Authorities from UserDetails: " + userDetails.getAuthorities());

            System.out.println("Loaded UserDetails for: " + username);

            if (jwtService.validateToken(token, userDetails)) {
                System.out.println("Token is VALID");

                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );

                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("Token is INVALID");
            }
        } else if (username == null) {
            System.out.println("Username is null, skipping authentication setup");
        } else {
            System.out.println("Already authenticated: " + SecurityContextHolder.getContext().getAuthentication());
        }

        filterChain.doFilter(request, response);
    }
}
