package com.example.bonjour20.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String category;

    @Column(length = 50)
    private String level; // BEGINNER, INTERMEDIATE, ADVANCED

    @Column(nullable = false)
    private BigDecimal price;

    private String thumbnailUrl;

    @Column(nullable = false)
    private Boolean isPublished = false;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", nullable = false)
    private User instructor;

    // Relation OneToMany avec Chapter
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Chapter> chapters = new ArrayList<>();

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

    // Méthodes utilitaires pour gérer la relation bidirectionnelle
    public void addChapter(Chapter chapter) {
        chapters.add(chapter);
        chapter.setCourse(this);
    }

    public void removeChapter(Chapter chapter) {
        chapters.remove(chapter);
        chapter.setCourse(null);
    }
}
