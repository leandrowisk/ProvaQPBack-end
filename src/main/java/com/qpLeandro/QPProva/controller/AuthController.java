package com.qpLeandro.QPProva.controller;
import com.qpLeandro.QPProva.repository.UsuarioRepository;
import com.qpLeandro.QPProva.service.JWTService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/v1/auth")
public class AuthController {
    private final UsuarioRepository repository;
    private final JWTService jwtService;


    public AuthController(
            UsuarioRepository repository,
            JWTService jwtService
    ) {

        this.repository = repository;
        this.jwtService = jwtService;
    }

    record LoginRequest(String email, String password) {}

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody LoginRequest request) {

        var user = repository.findByEmail(request.email());

        if (user.isEmpty() || !request.password().equals(user.get().getPassword())) {

            return ResponseEntity.status(401)
                    .body("Email ou senha inválidos");
        }

        String token = jwtService.gerarToken(request.email());

        return ResponseEntity.ok(Map.of("token", token));
    }
}
