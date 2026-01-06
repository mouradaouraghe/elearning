package com.example.bonjour20.dto;


import com.example.bonjour20.entities.LessonType;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonRequestDTO {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 200, message = "Title must be between 3 and 200 characters")
    private String title;

    @NotBlank(message = "Content is required")
    private String content;

    @NotNull(message = "Type is required")
    private LessonType type;

    private String videoUrl;

    @Positive(message = "Duration must be positive")
    private Integer durationInMinutes;

    @NotNull(message = "Order index is required")
    @Positive(message = "Order index must be positive")
    private Integer orderIndex;

    private Boolean isFree = false;

    @NotNull(message = "Chapter ID is required")
    private Long chapterId;
}
