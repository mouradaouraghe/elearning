package com.example.bonjour20.repository;

import com.example.bonjour20.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByInstructorId(Long instructorId);

    // Trouver tous les cours publiés
    List<Course> findByIsPublishedTrue();

    // Trouver les cours par catégorie
    List<Course> findByCategory(String category);

    // Trouver les cours par niveau
    List<Course> findByLevel(String level);

    // Recherche par titre (contient)
    List<Course> findByTitleContainingIgnoreCase(String keyword);

    // Requête personnalisée avec JPQL
    @Query("SELECT c FROM Course c WHERE c.isPublished = true AND c.instructor.id = :instructorId")
    List<Course> findPublishedCoursesByInstructor(Long instructorId);
}
