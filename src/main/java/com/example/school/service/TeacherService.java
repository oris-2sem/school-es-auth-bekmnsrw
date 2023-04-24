package com.example.school.service;

import com.example.school.dto.teacher.NewOrUpdatedTeacherDto;
import com.example.school.dto.teacher.TeacherDto;
import com.example.school.dto.teacher.TeacherPage;
import com.example.school.dto.timetable.TimetableLinePage;
import com.example.school.model.Teacher;

public interface TeacherService {

    TeacherDto findDtoById(Long teacherId);
    Teacher findById(Long teacherId);
    TeacherDto save(NewOrUpdatedTeacherDto newTeacherDto);
    void delete(Long teacherId);
    TeacherDto update(Long teacherId, NewOrUpdatedTeacherDto updatedTeacherDto);
    TeacherPage findAll(Integer page);
    TimetableLinePage findTimetableLines(Long teacherId, Integer page);
}
