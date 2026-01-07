package com.example.bonjour20.controller;

import com.example.bonjour20.dto.ChapterRequestDTO;
import com.example.bonjour20.dto.ChapterResponseDTO;
import com.example.bonjour20.services.ChapterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/chapters")
@RequiredArgsConstructor
@Slf4j
public class ChapterController {

    private final ChapterService chapterService;

    @PostMapping
    public ResponseEntity<ChapterResponseDTO> createChapter(@Valid @RequestBody ChapterRequestDTO requestDTO) {
        log.info("REST request to create chapter: {}", requestDTO.getTitle());
        ChapterResponseDTO responseDTO = chapterService.createChapter(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChapterResponseDTO> getChapterById(@PathVariable Long id) {
        log.info("REST request to get chapter with id: {}", id);
        ChapterResponseDTO chapter = chapterService.getChapterById(id);
        return ResponseEntity.ok(chapter);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<ChapterResponseDTO>> getChaptersByCourse(@PathVariable Long courseId) {
        log.info("REST request to get chapters for course: {}", courseId);
        List<ChapterResponseDTO> chapters = chapterService.getChaptersByCourse(courseId);
        return ResponseEntity.ok(chapters);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChapterResponseDTO> updateChapter(
            @PathVariable Long id,
            @Valid @RequestBody ChapterRequestDTO requestDTO) {
        log.info("REST request to update chapter with id: {}", id);
        ChapterResponseDTO updatedChapter = chapterService.updateChapter(id, requestDTO);
        return ResponseEntity.ok(updatedChapter);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChapter(@PathVariable Long id) {
        log.info("REST request to delete chapter with id: {}", id);
        chapterService.deleteChapter(id);
        return ResponseEntity.noContent().build();
    }
}