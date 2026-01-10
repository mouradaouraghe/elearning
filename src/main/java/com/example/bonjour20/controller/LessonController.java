package com.example.bonjour20.controller;

import com.example.bonjour20.dto.LessonRequestDTO;
import com.example.bonjour20.dto.LessonResponseDTO;
import com.example.bonjour20.services.LessonService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
//@RequiredArgsConstructor
@Slf4j
public class LessonController {
    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    private final LessonService lessonService;

    @PostMapping
    public ResponseEntity<LessonResponseDTO> createLesson(@Valid @RequestBody LessonRequestDTO requestDTO) {
        //log.info("REST request to create lesson: {}", requestDTO.getTitle());
        LessonResponseDTO responseDTO = lessonService.createLesson(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> getLessonById(@PathVariable Long id) {
        //log.info("REST request to get lesson with id: {}", id);
        LessonResponseDTO lesson = lessonService.getLessonById(id);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/chapter/{chapterId}")
    public ResponseEntity<List<LessonResponseDTO>> getLessonsByChapter(@PathVariable Long chapterId) {
        //log.info("REST request to get lessons for chapter: {}", chapterId);
        List<LessonResponseDTO> lessons = lessonService.getLessonsByChapter(chapterId);
        return ResponseEntity.ok(lessons);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponseDTO> updateLesson(
            @PathVariable Long id,
            @Valid @RequestBody LessonRequestDTO requestDTO) {
        //log.info("REST request to update lesson with id: {}", id);
        LessonResponseDTO updatedLesson = lessonService.updateLesson(id, requestDTO);
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        //log.info("REST request to delete lesson with id: {}", id);
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}