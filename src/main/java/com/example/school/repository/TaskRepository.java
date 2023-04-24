package com.example.school.repository;

import com.example.school.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task> findAllByStudentId(Long studentId, Pageable pageable);
    Page<Task> findAllByLessonId(Long lessonId, Pageable pageable);
}
