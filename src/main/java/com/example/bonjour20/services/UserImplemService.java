package com.example.bonjour20.services;

import com.example.bonjour20.dto.UserMapper;
import com.example.bonjour20.dto.UserRequestDTO;
import com.example.bonjour20.dto.UserResponseDTO;
import com.example.bonjour20.entities.User;
import com.example.bonjour20.exception.DuplicateResourceException;
import com.example.bonjour20.exception.ResourceNotFoundException;
import com.example.bonjour20.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j

@Service
@RequiredArgsConstructor

public class UserImplemService implements  UserService{

    private  final UserRepository userRepository;

    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;


    @Override
    public UserResponseDTO createUser(UserRequestDTO requestDTO) {
        //log.info("Creating user with email: {}", requestDTO.getEmail());

        // Vérifier si l'email existe déjà
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            //log.error("Email already exists: {}", requestDTO.getEmail());
            throw new DuplicateResourceException("User", "email", requestDTO.getEmail());
        }

        // Convertir DTO → Entity
        User user = userMapper.toEntity(requestDTO);

        // TODO: Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(requestDTO.getPassword()));


        // Sauvegarder
        User savedUser = userRepository.save(user);
        //log.info("User created successfully with id: {}", savedUser.getId());

        // Convertir Entity → ResponseDTO
        return userMapper.toResponseDTO(savedUser);
    }

    @Override
    public UserResponseDTO getUserById(Long id) {
        //log.info("Fetching user with id: {}", id);
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        return userMapper.toResponseDTO(user);
    }
    public UserResponseDTO getUserByEmail(String email) {
        //log.info("Fetching user with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return userMapper.toResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        //log.info("Fetching all users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    @Override
    public UserResponseDTO updateUser(Long id, UserRequestDTO requestDTO) {
        //log.info("Updating user with id: {}", id);

        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", id));

        // Mettre à jour les champs
        userMapper.updateEntityFromDTO(requestDTO, existingUser);

        User updatedUser = userRepository.save(existingUser);
        //log.info("User updated successfully");

        return userMapper.toResponseDTO(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        //log.info("Deleting user with id: {}", id);

        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("User", "id", id);
        }

        userRepository.deleteById(id);
        //log.info("User deleted successfully");
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
