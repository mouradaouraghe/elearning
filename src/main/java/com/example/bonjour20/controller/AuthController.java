package com.example.bonjour20.controller;


import com.example.bonjour20.config.CustomUserDetails;
import com.example.bonjour20.dto.*;
import com.example.bonjour20.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;
    private final UserMapper userMapper;

    public AuthController(AuthService authService, UserMapper userMapper) {
        this.authService = authService;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponseDTO> register(@Valid @RequestBody RegisterRequestDTO requestDTO) {
        //log.info("REST request to register user: {}", requestDTO.getEmail());
        AuthResponseDTO response = authService.register(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody LoginRequestDTO requestDTO) {
        //log.info("REST request to login user: {}", requestDTO.getEmail());
        AuthResponseDTO response = authService.login(requestDTO);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(
            @AuthenticationPrincipal CustomUserDetails userDetails) {
        //log.info("REST request to get current user");
        UserResponseDTO response = userMapper.toResponseDTO(userDetails.getUser());
        return ResponseEntity.ok(response);
    }
}