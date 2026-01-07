package com.example.bonjour20.services;

import com.example.bonjour20.dto.LessonRequestDTO;
import com.example.bonjour20.dto.LessonResponseDTO;

import java.util.List;

public interface LessonService {
    LessonResponseDTO createLesson(LessonRequestDTO requestDTO);

    LessonResponseDTO getLessonById(Long id);

    List<LessonResponseDTO> getLessonsByChapter(Long chapterId);

    LessonResponseDTO updateLesson(Long id, LessonRequestDTO requestDTO);

    void deleteLesson(Long id);
}
