package com.example.bonjour20.dto;


import com.example.bonjour20.entities.Chapter;
import com.example.bonjour20.entities.Lesson;
import org.springframework.stereotype.Component;

@Component
public class LessonMapper {

    public Lesson toEntity(LessonRequestDTO dto, Chapter chapter) {
        Lesson lesson = new Lesson();
        lesson.setTitle(dto.getTitle());
        lesson.setContent(dto.getContent());
        lesson.setType(dto.getType());
        lesson.setVideoUrl(dto.getVideoUrl());
        lesson.setDurationInMinutes(dto.getDurationInMinutes());
        lesson.setOrderIndex(dto.getOrderIndex());
        lesson.setIsFree(dto.getIsFree());
        lesson.setChapter(chapter);
        return lesson;
    }

    public LessonResponseDTO toResponseDTO(Lesson lesson) {
        LessonResponseDTO dto = new LessonResponseDTO();
        dto.setId(lesson.getId());
        dto.setTitle(lesson.getTitle());
        dto.setContent(lesson.getContent());
        dto.setType(lesson.getType());
        dto.setVideoUrl(lesson.getVideoUrl());
        dto.setDurationInMinutes(lesson.getDurationInMinutes());
        dto.setOrderIndex(lesson.getOrderIndex());
        dto.setIsFree(lesson.getIsFree());
        dto.setChapterId(lesson.getChapter().getId());
        dto.setCreatedAt(lesson.getCreatedAt());
        dto.setUpdatedAt(lesson.getUpdatedAt());
        return dto;
    }

    public void updateEntityFromDTO(LessonRequestDTO dto, Lesson lesson) {
        lesson.setTitle(dto.getTitle());
        lesson.setContent(dto.getContent());
        lesson.setType(dto.getType());
        lesson.setVideoUrl(dto.getVideoUrl());
        lesson.setDurationInMinutes(dto.getDurationInMinutes());
        lesson.setOrderIndex(dto.getOrderIndex());
        lesson.setIsFree(dto.getIsFree());
    }
}
