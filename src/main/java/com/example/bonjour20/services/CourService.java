package com.example.bonjour20.services;

import com.example.bonjour20.dto.CourseRequestDTO;
import com.example.bonjour20.dto.CourseResponseDTO;

import java.util.List;

public interface CourService {
    CourseResponseDTO createCourse(CourseRequestDTO requestDTO);

    CourseResponseDTO getCourseById(Long id);

    List<CourseResponseDTO> getAllCourses();

    List<CourseResponseDTO> getCoursesByInstructor(Long instructorId);

    List<CourseResponseDTO> getPublishedCourses();

    List<CourseResponseDTO> searchCourses(String keyword);

    CourseResponseDTO updateCourse(Long id, CourseRequestDTO requestDTO);

    CourseResponseDTO publishCourse(Long id);

    CourseResponseDTO unpublishCourse(Long id);

    void deleteCourse(Long id);
}
