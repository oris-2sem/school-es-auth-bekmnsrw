package com.example.school.service.impl;

import com.example.school.dto.clazz.ClazzDto;
import com.example.school.dto.clazz.NewOrUpdatedClazzDto;
import com.example.school.dto.student.StudentDto;
import com.example.school.dto.student.StudentPage;
import com.example.school.exception.NotFoundException;
import com.example.school.model.Clazz;
import com.example.school.model.Student;
import com.example.school.repository.ClazzRepository;
import com.example.school.service.ClazzService;
import com.example.school.service.StudentService;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClazzServiceImpl implements ClazzService {

    private final ClazzRepository clazzRepository;
    private final TeacherService teacherService;
    private final StudentService studentService;

    @Override
    public ClazzDto save(NewOrUpdatedClazzDto newClazzDto) {
        Clazz clazz = Clazz.builder()
                .yearOfBeginningOfStudying(newClazzDto.getYearOfBeginningOfStudying())
                .litera(newClazzDto.getLitera())
                .build();

        if (newClazzDto.getTeacherId() != null) {
            clazz.setTeacher(teacherService.findById(newClazzDto.getTeacherId()));
        }

        return ClazzDto.from(clazzRepository.save(clazz));
    }

    @Override
    public ClazzDto findDtoById(Long clazzId) {
        return ClazzDto.from(getOrThrow(clazzId));
    }

    @Override
    public Clazz findById(Long clazzId) {
        return getOrThrow(clazzId);
    }

    @Override
    public ClazzDto update(Long clazzId, NewOrUpdatedClazzDto updatedClazzDto) {
        Clazz clazz = getOrThrow(clazzId);

        clazz.setYearOfBeginningOfStudying(updatedClazzDto.getYearOfBeginningOfStudying());
        clazz.setLitera(updatedClazzDto.getLitera());

        if (updatedClazzDto.getTeacherId() != null) {
            clazz.setTeacher(teacherService.findById(updatedClazzDto.getTeacherId()));
        }

        return ClazzDto.from(clazzRepository.save(clazz));
    }

    @Override
    public void delete(Long clazzId) {
        clazzRepository.delete(getOrThrow(clazzId));
    }

    @Override
    public StudentPage addStudent(Long clazzId, Long studentId) {
        Clazz clazz = getOrThrow(clazzId);
        Student student = studentService.findById(studentId);

        clazz.getStudents().add(student);
        student.setClazz(clazz);
        clazzRepository.save(clazz);

        return StudentPage.builder()
                .students(StudentDto.from(clazz.getStudents()))
                .totalPages(clazz.getStudents().size())
                .build();
    }

    @Override
    public StudentPage getStudentsFromClazz(Long clazzId) {
        Clazz clazz = getOrThrow(clazzId);

        return StudentPage.builder()
                .students(StudentDto.from(clazz.getStudents()))
                .totalPages(clazz.getStudents().size())
                .build();
    }

    private Clazz getOrThrow(Long id) {
        return clazzRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find clazz with id: " + id));
    }
}
