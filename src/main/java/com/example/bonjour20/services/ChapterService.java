package com.example.bonjour20.services;


import com.example.bonjour20.dto.ChapterResponseDTO;
import com.example.bonjour20.dto.ChapterRequestDTO;
import java.util.List;

public interface ChapterService {

    ChapterResponseDTO createChapter(ChapterRequestDTO requestDTO);

    ChapterResponseDTO getChapterById(Long id);

    List<ChapterResponseDTO> getChaptersByCourse(Long courseId);

    ChapterResponseDTO updateChapter(Long id, ChapterRequestDTO requestDTO);

    void deleteChapter(Long id);
}
