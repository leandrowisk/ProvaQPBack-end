package com.qpLeandro.QPProva.security;
import com.qpLeandro.QPProva.repository.UsuarioRepository;
import com.qpLeandro.QPProva.service.JWTService;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JWTFilter extends OncePerRequestFilter {

    private final JWTService jwtService;
    private final UsuarioRepository repository;

    public JWTFilter(
            JWTService jwtService,
            UsuarioRepository repository) {
        this.jwtService = jwtService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain)
            throws ServletException, IOException {

        String authorization =
                request.getHeader("Authorization");

        if (authorization != null && authorization.startsWith("Bearer ")) {

            String token = authorization.substring(7);

            try {
                String email = jwtService.validarToken(token);

                if (SecurityContextHolder.getContext()
                        .getAuthentication() == null
                        && repository.findByEmail(email).isPresent()) {

                    var authentication =
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    null,
                                    List.of()
                            );

                    SecurityContextHolder.getContext()
                            .setAuthentication(authentication);
                }

            } catch (JwtException | IllegalArgumentException e) {
                SecurityContextHolder.clearContext();
                response.setHeader(
                        "Access-Control-Allow-Origin",
                        "http://localhost:3000"
                );

                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
        }

        chain.doFilter(request, response);
    }
}
