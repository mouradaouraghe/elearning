package com.example.bonjour20.services;

import com.example.bonjour20.dto.ChapterMapper;
import com.example.bonjour20.dto.ChapterRequestDTO;
import com.example.bonjour20.dto.ChapterResponseDTO;
import com.example.bonjour20.entities.Chapter;
import com.example.bonjour20.entities.Course;
import com.example.bonjour20.exception.ResourceNotFoundException;
import com.example.bonjour20.repository.ChapterRepository;
import com.example.bonjour20.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ChapterServiceImpl implements ChapterService {

    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;
    private final ChapterMapper chapterMapper;

    @Override
    public ChapterResponseDTO createChapter(ChapterRequestDTO requestDTO) {
        log.info("Creating chapter: {}", requestDTO.getTitle());

        // Vérifier que le cours existe
        Course course = courseRepository.findById(requestDTO.getCourseId())
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", requestDTO.getCourseId()));

        Chapter chapter = chapterMapper.toEntity(requestDTO, course);
        Chapter savedChapter = chapterRepository.save(chapter);

        log.info("Chapter created successfully with id: {}", savedChapter.getId());
        return chapterMapper.toResponseDTO(savedChapter);
    }

    @Override
    @Transactional(readOnly = true)
    public ChapterResponseDTO getChapterById(Long id) {
        log.info("Fetching chapter with id: {}", id);

        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chapter", "id", id));

        return chapterMapper.toResponseDTO(chapter);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChapterResponseDTO> getChaptersByCourse(Long courseId) {
        log.info("Fetching chapters for course: {}", courseId);

        // Vérifier que le cours existe
        if (!courseRepository.existsById(courseId)) {
            throw new ResourceNotFoundException("Course", "id", courseId);
        }

        return chapterRepository.findByCourseIdOrderByOrderIndexAsc(courseId).stream()
                .map(chapterMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ChapterResponseDTO updateChapter(Long id, ChapterRequestDTO requestDTO) {
        log.info("Updating chapter with id: {}", id);

        Chapter chapter = chapterRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Chapter", "id", id));

        chapterMapper.updateEntityFromDTO(requestDTO, chapter);
        Chapter updatedChapter = chapterRepository.save(chapter);

        log.info("Chapter updated successfully");
        return chapterMapper.toResponseDTO(updatedChapter);
    }

    @Override
    public void deleteChapter(Long id) {
        log.info("Deleting chapter with id: {}", id);

        if (!chapterRepository.existsById(id)) {
            throw new ResourceNotFoundException("Chapter", "id", id);
        }

        chapterRepository.deleteById(id);
        log.info("Chapter deleted successfully");
    }
}