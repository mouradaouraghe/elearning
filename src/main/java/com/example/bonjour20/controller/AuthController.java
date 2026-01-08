package com.example.bonjour20.controller;


import com.example.bonjour20.dto.AuthResponseDTO;
import com.example.bonjour20.dto.LoginRequestDTO;
import com.example.bonjour20.dto.RegisterRequestDTO;
import com.example.bonjour20.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        log.info("REST request to register user: {}", requestDTO.getEmail());
        AuthResponseDTO response = authService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO) {
        log.info("REST request to login user: {}", requestDTO.getEmail());
        AuthResponseDTO response = authService.login(requestDTO);
        return ResponseEntity.ok(response);
    }
}