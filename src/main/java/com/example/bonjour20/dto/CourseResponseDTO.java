package com.example.bonjour20.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String level;
    private BigDecimal price;
    private String thumbnailUrl;
    private Boolean isPublished;

    // Informations de l'instructeur (nested DTO)
    private InstructorSummaryDTO instructor;

    // Statistiques
    private Integer totalChapters;
    private Integer totalLessons;
    private Integer totalDurationInMinutes;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
