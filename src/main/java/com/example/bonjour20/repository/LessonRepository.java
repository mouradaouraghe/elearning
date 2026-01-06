package com.example.bonjour20.repository;

import com.example.bonjour20.entities.Lesson;
import com.example.bonjour20.entities.LessonType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByChapterIdOrderByOrderIndexAsc(Long chapterId);

    // Trouver les leçons gratuites d'un cours
    List<Lesson> findByChapterCourseIdAndIsFreeTrue(Long courseId);

    // Trouver les leçons par type
    List<Lesson> findByType(LessonType type);

    // Compter les leçons d'un chapitre
    long countByChapterId(Long chapterId);
}
