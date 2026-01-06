package com.example.bonjour20.dto;


import com.example.bonjour20.entities.Chapter;
import com.example.bonjour20.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class ChapterMapper {

    public Chapter toEntity(ChapterRequestDTO dto, Course course) {
        Chapter chapter = new Chapter();
        chapter.setTitle(dto.getTitle());
        chapter.setDescription(dto.getDescription());
        chapter.setOrderIndex(dto.getOrderIndex());
        chapter.setCourse(course);
        return chapter;
    }

    public ChapterResponseDTO toResponseDTO(Chapter chapter) {
        ChapterResponseDTO dto = new ChapterResponseDTO();
        dto.setId(chapter.getId());
        dto.setTitle(chapter.getTitle());
        dto.setDescription(chapter.getDescription());
        dto.setOrderIndex(chapter.getOrderIndex());
        dto.setCourseId(chapter.getCourse().getId());
        dto.setCreatedAt(chapter.getCreatedAt());
        dto.setUpdatedAt(chapter.getUpdatedAt());
        return dto;
    }

    public ChapterResponseDTO toResponseDTOWithLessons(Chapter chapter, LessonMapper lessonMapper) {
        ChapterResponseDTO dto = toResponseDTO(chapter);
        dto.setLessons(chapter.getLessons().stream()
                .map(lessonMapper::toResponseDTO)
                .toList());
        return dto;
    }

    public void updateEntityFromDTO(ChapterRequestDTO dto, Chapter chapter) {
        chapter.setTitle(dto.getTitle());
        chapter.setDescription(dto.getDescription());
        chapter.setOrderIndex(dto.getOrderIndex());
    }
}
