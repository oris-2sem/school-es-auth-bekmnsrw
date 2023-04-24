package com.example.school.repository;

import com.example.school.model.TimetableLine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimetableLineRepository extends JpaRepository<TimetableLine, Long> {
    Page<TimetableLine> findAllByTeacherId(Long teacherId, Pageable pageable);
    Page<TimetableLine> findAllByLessonId(Long lessonId, Pageable pageable);
}
