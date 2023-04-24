package com.example.school.service;

import com.example.school.dto.parent.NewOrUpdatedParentDto;
import com.example.school.dto.parent.ParentDto;
import com.example.school.dto.parent.ParentPage;
import com.example.school.dto.student.StudentPage;
import com.example.school.model.Parent;

public interface ParentService {

    ParentDto save(NewOrUpdatedParentDto newParentDto);
    ParentDto update(Long parentId, NewOrUpdatedParentDto updatedParentDto);
    ParentDto findDtoById(Long parentId);
    Parent findById(Long parentId);
    void delete(Long parentId);
    ParentPage findAll(Integer page);
    StudentPage addChild(Long parentId, Long studentId);
    StudentPage getChildren(Long parentId);
}
