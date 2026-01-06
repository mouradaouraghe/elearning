package com.example.bonjour20.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChapterResponseDTO {

    private Long id;
    private String title;
    private String description;
    private Integer orderIndex;
    private Long courseId;

    // Liste des leçons (optionnelle, chargée à la demande)
    private List<LessonResponseDTO> lessons = new ArrayList<>();

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}