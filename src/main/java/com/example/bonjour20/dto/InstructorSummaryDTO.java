package com.example.bonjour20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InstructorSummaryDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    // Méthode utilitaire
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
