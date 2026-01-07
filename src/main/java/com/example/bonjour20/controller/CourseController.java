package com.example.bonjour20.controller;

import com.example.bonjour20.dto.CourseRequestDTO;
import com.example.bonjour20.dto.CourseResponseDTO;
import com.example.bonjour20.services.CourService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
@RequiredArgsConstructor
public class CourseController {
    private final CourService courseService;

    // Créer un cours
    @PostMapping
    public ResponseEntity<CourseResponseDTO> createCourse(@Valid @RequestBody CourseRequestDTO requestDTO) {
        //log.info("REST request to create course: {}", requestDTO.getTitle());
        CourseResponseDTO responseDTO = courseService.createCourse(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    // Récupérer tous les cours
    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses() {
        //log.info("REST request to get all courses");
        List<CourseResponseDTO> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    // Récupérer un cours par ID
    @GetMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Long id) {
        //log.info("REST request to get course with id: {}", id);
        CourseResponseDTO course = courseService.getCourseById(id);
        return ResponseEntity.ok(course);
    }

    // Récupérer les cours d'un instructeur
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<CourseResponseDTO>> getCoursesByInstructor(@PathVariable Long instructorId) {
        //log.info("REST request to get courses for instructor: {}", instructorId);
        List<CourseResponseDTO> courses = courseService.getCoursesByInstructor(instructorId);
        return ResponseEntity.ok(courses);
    }

    // Récupérer tous les cours publiés
    @GetMapping("/published")
    public ResponseEntity<List<CourseResponseDTO>> getPublishedCourses() {
        //log.info("REST request to get all published courses");
        List<CourseResponseDTO> courses = courseService.getPublishedCourses();
        return ResponseEntity.ok(courses);
    }

    // Rechercher des cours
    @GetMapping("/search")
    public ResponseEntity<List<CourseResponseDTO>> searchCourses(@RequestParam String keyword) {
        //log.info("REST request to search courses with keyword: {}", keyword);
        List<CourseResponseDTO> courses = courseService.searchCourses(keyword);
        return ResponseEntity.ok(courses);
    }

    // Mettre à jour un cours
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourse(
            @PathVariable Long id,
            @Valid @RequestBody CourseRequestDTO requestDTO) {
        //log.info("REST request to update course with id: {}", id);
        CourseResponseDTO updatedCourse = courseService.updateCourse(id, requestDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    // Publier un cours
    @PatchMapping("/{id}/publish")
    public ResponseEntity<CourseResponseDTO> publishCourse(@PathVariable Long id) {
        //log.info("REST request to publish course with id: {}", id);
        CourseResponseDTO publishedCourse = courseService.publishCourse(id);
        return ResponseEntity.ok(publishedCourse);
    }

    // Dépublier un cours
    @PatchMapping("/{id}/unpublish")
    public ResponseEntity<CourseResponseDTO> unpublishCourse(@PathVariable Long id) {
        //log.info("REST request to unpublish course with id: {}", id);
        CourseResponseDTO unpublishedCourse = courseService.unpublishCourse(id);
        return ResponseEntity.ok(unpublishedCourse);
    }

    // Supprimer un cours
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        //log.info("REST request to delete course with id: {}", id);
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
