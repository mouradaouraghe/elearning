package com.example.bonjour20.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseRequestDTO {
    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 200, message = "Title must be between 5 and 200 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 20, message = "Description must be at least 20 characters")
    private String description;

    @NotBlank(message = "Category is required")
    private String category;

    @NotBlank(message = "Level is required")
    @Pattern(regexp = "BEGINNER|INTERMEDIATE|ADVANCED", message = "Level must be BEGINNER, INTERMEDIATE, or ADVANCED")
    private String level;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", message = "Price must be positive")
    private BigDecimal price;

    private String thumbnailUrl;

    @NotNull(message = "Instructor ID is required")
    private Long instructorId;
}
