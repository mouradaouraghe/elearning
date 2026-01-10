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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public Boolean getIsPublished() {
        return isPublished;
    }

    public void setIsPublished(Boolean published) {
        isPublished = published;
    }

    public InstructorSummaryDTO getInstructor() {
        return instructor;
    }

    public void setInstructor(InstructorSummaryDTO instructor) {
        this.instructor = instructor;
    }

    public Integer getTotalChapters() {
        return totalChapters;
    }

    public void setTotalChapters(Integer totalChapters) {
        this.totalChapters = totalChapters;
    }

    public Integer getTotalLessons() {
        return totalLessons;
    }

    public void setTotalLessons(Integer totalLessons) {
        this.totalLessons = totalLessons;
    }

    public Integer getTotalDurationInMinutes() {
        return totalDurationInMinutes;
    }

    public void setTotalDurationInMinutes(Integer totalDurationInMinutes) {
        this.totalDurationInMinutes = totalDurationInMinutes;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

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
