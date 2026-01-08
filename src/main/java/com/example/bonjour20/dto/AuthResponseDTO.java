package com.example.bonjour20.dto;

import com.example.bonjour20.entities.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AuthResponseDTO {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private Role role;

    // Constructeur sans le type (Bearer est la valeur par défaut)
    public AuthResponseDTO(String token, Long id, String email, String firstName, String lastName, Role role) {
        this.token = token;
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}