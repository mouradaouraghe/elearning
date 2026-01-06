package com.example.bonjour20.dto;


import com.example.bonjour20.entities.User;
import com.example.bonjour20.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CourseMapper {

    public Course toEntity(CourseRequestDTO dto, User instructor) {
        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setCategory(dto.getCategory());
        course.setLevel(dto.getLevel());
        course.setPrice(dto.getPrice());
        course.setThumbnailUrl(dto.getThumbnailUrl());
        course.setInstructor(instructor);
        course.setIsPublished(false); // Par défaut non publié
        return course;
    }

    public CourseResponseDTO toResponseDTO(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setCategory(course.getCategory());
        dto.setLevel(course.getLevel());
        dto.setPrice(course.getPrice());
        dto.setThumbnailUrl(course.getThumbnailUrl());
        dto.setIsPublished(course.getIsPublished());
        dto.setCreatedAt(course.getCreatedAt());
        dto.setUpdatedAt(course.getUpdatedAt());

        // Mapper l'instructeur
        if (course.getInstructor() != null) {
            User instructor = course.getInstructor();
            InstructorSummaryDTO instructorDTO = new InstructorSummaryDTO(
                    instructor.getId(),
                    instructor.getFirstName(),
                    instructor.getLastName(),
                    instructor.getEmail()
            );
            dto.setInstructor(instructorDTO);
        }

        // Calculer les statistiques
        dto.setTotalChapters(course.getChapters().size());

        int totalLessons = course.getChapters().stream()
                .mapToInt(chapter -> chapter.getLessons().size())
                .sum();
        dto.setTotalLessons(totalLessons);

        int totalDuration = course.getChapters().stream()
                .flatMap(chapter -> chapter.getLessons().stream())
                .mapToInt(lesson -> lesson.getDurationInMinutes() != null ? lesson.getDurationInMinutes() : 0)
                .sum();
        dto.setTotalDurationInMinutes(totalDuration);

        return dto;
    }

    public void updateEntityFromDTO(CourseRequestDTO dto, Course course) {
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setCategory(dto.getCategory());
        course.setLevel(dto.getLevel());
        course.setPrice(dto.getPrice());
        course.setThumbnailUrl(dto.getThumbnailUrl());
    }
}
