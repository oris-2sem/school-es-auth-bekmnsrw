package com.example.school.service.impl;

import com.example.school.dto.student.NewOrUpdatedStudentDto;
import com.example.school.dto.student.StudentDto;
import com.example.school.dto.student.StudentPage;
import com.example.school.dto.task.TaskDto;
import com.example.school.dto.task.TaskPage;
import com.example.school.exception.NotFoundException;
import com.example.school.model.Clazz;
import com.example.school.model.Parent;
import com.example.school.model.Student;
import com.example.school.model.Task;
import com.example.school.repository.ClazzRepository;
import com.example.school.repository.ParentRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.repository.TaskRepository;
import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final ClazzRepository clazzRepository;
    private final ParentRepository parentRepository;
    private final TaskRepository taskRepository;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public Student findById(Long studentId) {
        return getOrThrow(studentId);
    }

    @Override
    public StudentDto findDtoById(Long studentId) {
        return StudentDto.from(getOrThrow(studentId));
    }

    @Override
    public StudentDto save(NewOrUpdatedStudentDto newStudentDto) {
        Student student = Student.builder()
                .firstName(newStudentDto.getFirstName())
                .secondName(newStudentDto.getSecondName())
                .patronymic(newStudentDto.getPatronymic())
                .build();

        if (newStudentDto.getClazzId() != null) {
            Optional<Clazz> clazz = clazzRepository.findById(newStudentDto.getClazzId());
            clazz.ifPresent(student::setClazz);
        }

        if (newStudentDto.getParentId() != null) {
            Optional<Parent> parent = parentRepository.findById(newStudentDto.getParentId());
            parent.ifPresent(student::setParent);
        }

        return StudentDto.from(studentRepository.save(student));
    }

    @Override
    public StudentDto update(Long studentId, NewOrUpdatedStudentDto updatedStudentDto) {
        Student student = getOrThrow(studentId);

        student.setFirstName(updatedStudentDto.getFirstName());
        student.setSecondName(updatedStudentDto.getSecondName());
        student.setPatronymic(updatedStudentDto.getPatronymic());

        if (updatedStudentDto.getClazzId() != null) {
            Optional<Clazz> clazz = clazzRepository.findById(updatedStudentDto.getClazzId());
            clazz.ifPresent(student::setClazz);
        } else {
            student.setClazz(null);
        }

        if (updatedStudentDto.getParentId() != null) {
            Optional<Parent> parent = parentRepository.findById(updatedStudentDto.getParentId());
            parent.ifPresent(student::setParent);
        } else {
            student.setParent(null);
        }

        return StudentDto.from(studentRepository.save(student));
    }

    @Override
    public void delete(Long studentId) {
        studentRepository.delete(getOrThrow(studentId));
    }

    @Override
    public StudentPage findAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Student> studentPage = studentRepository.findAll(pageRequest);

        return StudentPage.builder()
                .students(StudentDto.from(studentPage.getContent()))
                .totalPages(studentPage.getTotalPages())
                .build();
    }

    @Override
    public TaskPage getTasks(Long studentId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Task> taskPage = taskRepository.findAllByStudentId(studentId, pageRequest);

        return TaskPage.builder()
                .tasks(TaskDto.from(taskPage.getContent()))
                .totalPages(taskPage.getTotalPages())
                .build();
    }

    private Student getOrThrow(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find student with id: " + id));
    }
}
