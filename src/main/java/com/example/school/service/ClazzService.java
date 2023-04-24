package com.example.school.service;

import com.example.school.dto.clazz.ClazzDto;
import com.example.school.dto.clazz.NewOrUpdatedClazzDto;
import com.example.school.dto.student.StudentPage;
import com.example.school.model.Clazz;

public interface ClazzService {

    ClazzDto save(NewOrUpdatedClazzDto newClazzDto);
    ClazzDto findDtoById(Long clazzId);
    Clazz findById(Long clazzId);
    ClazzDto update(Long clazzId, NewOrUpdatedClazzDto updatedClazzDto);
    void delete(Long clazzId);
    StudentPage addStudent(Long clazzId, Long studentId);
    StudentPage getStudentsFromClazz(Long clazzId);
}
