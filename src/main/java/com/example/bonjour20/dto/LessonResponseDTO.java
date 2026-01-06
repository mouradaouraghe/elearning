package com.example.bonjour20.dto;


import com.example.bonjour20.entities.LessonType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonResponseDTO {

    private Long id;
    private String title;
    private String content;
    private LessonType type;
    private String videoUrl;
    private Integer durationInMinutes;
    private Integer orderIndex;
    private Boolean isFree;
    private Long chapterId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
