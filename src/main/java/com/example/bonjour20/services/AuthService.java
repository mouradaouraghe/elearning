package com.example.bonjour20.services;

import com.example.bonjour20.dto.AuthResponseDTO;
import com.example.bonjour20.dto.LoginRequestDTO;
import com.example.bonjour20.dto.RegisterRequestDTO;

public interface AuthService {
    AuthResponseDTO register(RegisterRequestDTO requestDTO);

    AuthResponseDTO login(LoginRequestDTO requestDTO);
}
