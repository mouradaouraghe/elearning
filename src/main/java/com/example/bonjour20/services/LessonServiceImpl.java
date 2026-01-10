package com.example.bonjour20.services;

import com.example.bonjour20.dto.LessonMapper;
import com.example.bonjour20.dto.LessonRequestDTO;
import com.example.bonjour20.dto.LessonResponseDTO;
import com.example.bonjour20.entities.Chapter;
import com.example.bonjour20.entities.Lesson;
import com.example.bonjour20.exception.ResourceNotFoundException;
import com.example.bonjour20.repository.ChapterRepository;
import com.example.bonjour20.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
   // @RequiredArgsConstructor
    @Slf4j
    @Transactional
    public class LessonServiceImpl implements LessonService {
    public LessonServiceImpl(LessonRepository lessonRepository, ChapterRepository chapterRepository, LessonMapper lessonMapper) {
        this.lessonRepository = lessonRepository;
        this.chapterRepository = chapterRepository;
        this.lessonMapper = lessonMapper;
    }

    private final LessonRepository lessonRepository;
        private final ChapterRepository chapterRepository;
        private final LessonMapper lessonMapper;

        @Override
        public LessonResponseDTO createLesson(LessonRequestDTO requestDTO) {
            //log.info("Creating lesson: {}", requestDTO.getTitle());

            // Vérifier que le chapitre existe
            Chapter chapter = chapterRepository.findById(requestDTO.getChapterId())
                    .orElseThrow(() -> new ResourceNotFoundException("Chapter", "id", requestDTO.getChapterId()));

            Lesson lesson = lessonMapper.toEntity(requestDTO, chapter);
            Lesson savedLesson = lessonRepository.save(lesson);

            //log.info("Lesson created successfully with id: {}", savedLesson.getId());
            return lessonMapper.toResponseDTO(savedLesson);
        }

        @Override
        @Transactional(readOnly = true)
        public LessonResponseDTO getLessonById(Long id) {
            //log.info("Fetching lesson with id: {}", id);

            Lesson lesson = lessonRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Lesson", "id", id));

            return lessonMapper.toResponseDTO(lesson);
        }

        @Override
        @Transactional(readOnly = true)
        public List<LessonResponseDTO> getLessonsByChapter(Long chapterId) {
            //log.info("Fetching lessons for chapter: {}", chapterId);

            // Vérifier que le chapitre existe
            if (!chapterRepository.existsById(chapterId)) {
                throw new ResourceNotFoundException("Chapter", "id", chapterId);
            }

            return lessonRepository.findByChapterIdOrderByOrderIndexAsc(chapterId).stream()
                    .map(lessonMapper::toResponseDTO)
                    .collect(Collectors.toList());
        }

        @Override
        public LessonResponseDTO updateLesson(Long id, LessonRequestDTO requestDTO) {
            //log.info("Updating lesson with id: {}", id);

            Lesson lesson = lessonRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Lesson", "id", id));

            lessonMapper.updateEntityFromDTO(requestDTO, lesson);
            Lesson updatedLesson = lessonRepository.save(lesson);

            //log.info("Lesson updated successfully");
            return lessonMapper.toResponseDTO(updatedLesson);
        }

        @Override
        public void deleteLesson(Long id) {
            //log.info("Deleting lesson with id: {}", id);

            if (!lessonRepository.existsById(id)) {
                throw new ResourceNotFoundException("Lesson", "id", id);
            }

            lessonRepository.deleteById(id);
            //log.info("Lesson deleted successfully");
        }
}
