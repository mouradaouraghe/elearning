package com.example.bonjour20.services;

import com.example.bonjour20.dto.CourseMapper;
import com.example.bonjour20.dto.CourseRequestDTO;
import com.example.bonjour20.dto.CourseResponseDTO;
import com.example.bonjour20.entities.Course;
import com.example.bonjour20.entities.Role;
import com.example.bonjour20.entities.User;
import com.example.bonjour20.exception.ResourceNotFoundException;
import com.example.bonjour20.repository.CourseRepository;
import com.example.bonjour20.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
//@RequiredArgsConstructor
@Slf4j
@Transactional
public class CourseServiceImpl implements CourService{
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository, UserRepository userRepository, CourseMapper courseMapper) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.courseMapper = courseMapper;
    }

    private final UserRepository userRepository;
    private final CourseMapper courseMapper;

    @Override
    public CourseResponseDTO createCourse(CourseRequestDTO requestDTO) {
        //log.info("Creating course: {}", requestDTO.getTitle());

        // Vérifier que l'instructeur existe et a le bon rôle
        User instructor = userRepository.findById(requestDTO.getInstructorId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", requestDTO.getInstructorId()));

        if (instructor.getRole() != Role.INSTRUCTOR && instructor.getRole() != Role.ADMIN) {
            throw new RuntimeException("User is not an instructor");
        }

        // Créer le cours
        Course course = courseMapper.toEntity(requestDTO, instructor);
        Course savedCourse = courseRepository.save(course);

        //log.info("Course created successfully with id: {}", savedCourse.getId());
        return courseMapper.toResponseDTO(savedCourse);
    }

    @Override
    @Transactional(readOnly = true)
    public CourseResponseDTO getCourseById(Long id) {
        //log.info("Fetching course with id: {}", id);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        return courseMapper.toResponseDTO(course);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getAllCourses() {
        //log.info("Fetching all courses");

        return courseRepository.findAll().stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getCoursesByInstructor(Long instructorId) {
        //log.info("Fetching courses for instructor: {}", instructorId);

        // Vérifier que l'instructeur existe
        if (!userRepository.existsById(instructorId)) {
            throw new ResourceNotFoundException("User", "id", instructorId);
        }

        return courseRepository.findByInstructorId(instructorId).stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> getPublishedCourses() {
        //log.info("Fetching all published courses");

        return courseRepository.findByIsPublishedTrue().stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<CourseResponseDTO> searchCourses(String keyword) {
        //log.info("Searching courses with keyword: {}", keyword);

        return courseRepository.findByTitleContainingIgnoreCase(keyword).stream()
                .map(courseMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CourseResponseDTO updateCourse(Long id, CourseRequestDTO requestDTO) {
        //log.info("Updating course with id: {}", id);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        courseMapper.updateEntityFromDTO(requestDTO, course);
        Course updatedCourse = courseRepository.save(course);

        //log.info("Course updated successfully");
        return courseMapper.toResponseDTO(updatedCourse);
    }

    @Override
    public CourseResponseDTO publishCourse(Long id) {
        //log.info("Publishing course with id: {}", id);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        course.setIsPublished(true);
        Course publishedCourse = courseRepository.save(course);

        //log.info("Course published successfully");
        return courseMapper.toResponseDTO(publishedCourse);
    }

    @Override
    public CourseResponseDTO unpublishCourse(Long id) {
        //log.info("Unpublishing course with id: {}", id);

        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));

        course.setIsPublished(false);
        Course unpublishedCourse = courseRepository.save(course);

        //log.info("Course unpublished successfully");
        return courseMapper.toResponseDTO(unpublishedCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        //log.info("Deleting course with id: {}", id);

        if (!courseRepository.existsById(id)) {
            throw new ResourceNotFoundException("Course", "id", id);
        }

        courseRepository.deleteById(id);
        //log.info("Course deleted successfully");
    }
}
