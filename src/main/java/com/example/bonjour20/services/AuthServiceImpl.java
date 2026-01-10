package com.example.bonjour20.services;

import com.example.bonjour20.config.CustomUserDetails;
import com.example.bonjour20.config.JwtUtil;
import com.example.bonjour20.dto.AuthResponseDTO;
import com.example.bonjour20.dto.LoginRequestDTO;
import com.example.bonjour20.dto.RegisterRequestDTO;
import com.example.bonjour20.entities.User;
import com.example.bonjour20.exception.DuplicateResourceException;
import com.example.bonjour20.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//@RequiredArgsConstructor
@Slf4j
@Transactional
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    @Override
    public AuthResponseDTO register(RegisterRequestDTO requestDTO) {
        //log.info("Registering new user with email: {}", requestDTO.getEmail());

        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            //log.error("Email already exists: {}", requestDTO.getEmail());
            throw new DuplicateResourceException("User", "email", requestDTO.getEmail());
        }

        // Créer l'utilisateur
        User user = new User();
        user.setEmail(requestDTO.getEmail());
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));
        user.setFirstName(requestDTO.getFirstName());
        user.setLastName(requestDTO.getLastName());
        user.setRole(requestDTO.getRole());

        User savedUser = userRepository.save(user);
        //log.info("User registered successfully with id: {}", savedUser.getId());

        // Générer le token JWT
        CustomUserDetails userDetails = new CustomUserDetails(savedUser);
        String token = jwtUtil.generateToken(userDetails);

        // Retourner la réponse
        return new AuthResponseDTO(
                token,
                savedUser.getId(),
                savedUser.getEmail(),
                savedUser.getFirstName(),
                savedUser.getLastName(),
                savedUser.getRole()
        );
    }

    @Override
    public AuthResponseDTO login(LoginRequestDTO requestDTO) {
        //log.info("User attempting to login with email: {}", requestDTO.getEmail());

        try {
            // Authentifier l'utilisateur
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDTO.getEmail(),
                            requestDTO.getPassword()
                    )
            );

            // Récupérer les détails de l'utilisateur authentifié
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            User user = userDetails.getUser();

            // Générer le token JWT
            String token = jwtUtil.generateToken(userDetails);

            //log.info("User logged in successfully: {}", user.getEmail());

            // Retourner la réponse
            return new AuthResponseDTO(
                    token,
                    user.getId(),
                    user.getEmail(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole()
            );

        } catch (BadCredentialsException e) {
            //log.error("Invalid credentials for email: {}", requestDTO.getEmail());
            throw new BadCredentialsException("Invalid email or password");
        }
    }
}
