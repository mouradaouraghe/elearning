package com.example.bonjour20.controller;

import com.example.bonjour20.dto.UserRequestDTO;
import com.example.bonjour20.dto.UserResponseDTO;
import com.example.bonjour20.entities.User;
import com.example.bonjour20.services.UserImplemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@Slf4j
@RequiredArgsConstructor

public class UserController {
    private final UserImplemService userService;

   @PostMapping
   public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO requestDTO) {
       //log.info("REST request to create user: {}", requestDTO.getEmail());
       UserResponseDTO responseDTO = userService.createUser(requestDTO);
       return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
   }
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        //log.info("REST request to get all users");
        List<UserResponseDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        //log.info("REST request to get user with id: {}", id);
        UserResponseDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO requestDTO) {
        //log.info("REST request to update user with id: {}", id);
        UserResponseDTO updatedUser = userService.updateUser(id, requestDTO);
        return ResponseEntity.ok(updatedUser);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        //log.info("REST request to delete user with id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
