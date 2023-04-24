package com.example.school.service;

import com.example.school.dto.student.NewOrUpdatedStudentDto;
import com.example.school.dto.student.StudentDto;
import com.example.school.dto.student.StudentPage;
import com.example.school.dto.task.TaskPage;
import com.example.school.model.Student;

public interface StudentService {
    Student findById(Long studentId);
    StudentDto findDtoById(Long studentId);
    StudentDto save(NewOrUpdatedStudentDto newStudentDto);
    StudentDto update(Long studentId, NewOrUpdatedStudentDto updatedStudentDto);
    void delete(Long studentId);
    StudentPage findAll(Integer page);
    TaskPage getTasks(Long studentId, Integer page);
}
