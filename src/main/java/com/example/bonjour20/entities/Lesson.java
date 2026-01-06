package com.example.bonjour20.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LessonType type; // VIDEO, ARTICLE, QUIZ

    private String videoUrl;

    private Integer durationInMinutes;

    @Column(nullable = false)
    private Integer orderIndex;

    @Column(nullable = false)
    private Boolean isFree = false; // Leçon gratuite pour aperçu

    // Relation ManyToOne avec Chapter
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}