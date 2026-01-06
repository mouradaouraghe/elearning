package com.example.bonjour20.repository;

import com.example.bonjour20.entities.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    List<Chapter> findByCourseIdOrderByOrderIndexAsc(Long courseId);

    // Compter les chapitres d'un cours
    long countByCourseId(Long courseId);
}
