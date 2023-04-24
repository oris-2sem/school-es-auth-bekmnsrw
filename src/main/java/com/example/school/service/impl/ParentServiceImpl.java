package com.example.school.service.impl;

import com.example.school.dto.parent.NewOrUpdatedParentDto;
import com.example.school.dto.parent.ParentDto;
import com.example.school.dto.parent.ParentPage;
import com.example.school.dto.student.StudentDto;
import com.example.school.dto.student.StudentPage;
import com.example.school.exception.NotFoundException;
import com.example.school.model.Parent;
import com.example.school.model.Student;
import com.example.school.repository.ParentRepository;
import com.example.school.repository.StudentRepository;
import com.example.school.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentServiceImpl implements ParentService {

    private final ParentRepository parentRepository;
    private final StudentRepository studentRepository;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public ParentDto save(NewOrUpdatedParentDto newParentDto) {
        Parent parent = Parent.builder()
                .firstName(newParentDto.getFirstName())
                .secondName(newParentDto.getSecondName())
                .patronymic(newParentDto.getPatronymic())
                .build();

        return ParentDto.from(parentRepository.save(parent));
    }

    @Override
    public ParentDto update(Long parentId, NewOrUpdatedParentDto updatedParentDto) {
        Parent parent = getOrThrow(parentId);

        parent.setFirstName(updatedParentDto.getFirstName());
        parent.setSecondName(updatedParentDto.getSecondName());
        parent.setPatronymic(updatedParentDto.getPatronymic());

        return ParentDto.from(parentRepository.save(parent));
    }

    @Override
    public ParentDto findDtoById(Long parentId) {
        return ParentDto.from(getOrThrow(parentId));
    }

    @Override
    public Parent findById(Long parentId) {
        return getOrThrow(parentId);
    }

    @Override
    public void delete(Long parentId) {
        parentRepository.delete(getOrThrow(parentId));
    }

    @Override
    public ParentPage findAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Parent> parentPage = parentRepository.findAll(pageRequest);

        return ParentPage.builder()
                .parents(ParentDto.from(parentPage.getContent()))
                .totalPages(parentPage.getTotalPages())
                .build();
    }

    @Override
    public StudentPage addChild(Long parentId, Long studentId) {
        Parent parent = findById(parentId);
        Optional<Student> child = studentRepository.findById(studentId);

        if (child.isPresent()) {
            parent.getChildren().add(child.get());
            child.get().setParent(parent);
            studentRepository.save(child.get());
        }

        return StudentPage.builder()
                .students(StudentDto.from(parent.getChildren()))
                .totalPages(parent.getChildren().size())
                .build();
    }

    @Override
    public StudentPage getChildren(Long parentId) {
        Parent parent = findById(parentId);

        return StudentPage.builder()
                .students(StudentDto.from(parent.getChildren()))
                .totalPages(parent.getChildren().size())
                .build();
    }

    private Parent getOrThrow(Long id) {
        return parentRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find parent with id: " + id));
    }
}
