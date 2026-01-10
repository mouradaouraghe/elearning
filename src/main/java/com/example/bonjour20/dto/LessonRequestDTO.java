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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LessonType getType() {
        return type;
    }

    public void setType(LessonType type) {
        this.type = type;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public Integer getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(Integer durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Boolean getIsFree() {
        return isFree;
    }

    public void setIsFree(Boolean free) {
        isFree = free;
    }

    public Long getChapterId() {
        return chapterId;
    }

    public void setChapterId(Long chapterId) {
        this.chapterId = chapterId;
    }

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
