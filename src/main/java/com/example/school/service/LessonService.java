package com.example.school.service;

import com.example.school.dto.lesson.LessonDto;
import com.example.school.dto.lesson.LessonPage;
import com.example.school.dto.lesson.NewOrUpdatedLessonDto;
import com.example.school.dto.task.TaskPage;
import com.example.school.dto.timetable.TimetableLinePage;
import com.example.school.model.Lesson;

public interface LessonService {
    LessonDto save(NewOrUpdatedLessonDto newLessonDto);
    LessonDto update(Long lessonId, NewOrUpdatedLessonDto updatedLessonDto);
    void delete(Long lessonId);
    Lesson findById(Long lessonId);
    LessonDto findDtoById(Long lessonId);
    LessonPage findAll(Integer page);
    TaskPage getTasks(Long lessonId, Integer page);
    TimetableLinePage getTimetableLines(Long lessonId, Integer page);
}
