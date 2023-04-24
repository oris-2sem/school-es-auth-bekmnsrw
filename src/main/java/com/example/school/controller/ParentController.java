package com.example.school.controller;

import com.example.school.dto.parent.NewOrUpdatedParentDto;
import com.example.school.dto.parent.ParentDto;
import com.example.school.dto.parent.ParentPage;
import com.example.school.dto.student.StudentPage;
import com.example.school.service.ParentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/parents")
public class ParentController {

    private final ParentService parentService;

    @PostMapping
    public ResponseEntity<ParentDto> save(
            @RequestBody NewOrUpdatedParentDto newParentDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parentService.save(newParentDto));
    }

    @GetMapping("/{parent-id}")
    public ResponseEntity<ParentDto> getById(
            @PathVariable("parent-id") Long parentId
    ) {
        return ResponseEntity
                .ok(parentService.findDtoById(parentId));
    }

    @PutMapping("/{parent-id}")
    public ResponseEntity<ParentDto> update(
            @PathVariable("parent-id") Long parentId,
            @RequestBody NewOrUpdatedParentDto updatedParentDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(parentService.update(parentId, updatedParentDto));
    }

    @DeleteMapping("/{parent-id}")
    public ResponseEntity<?> delete(
            @PathVariable("parent-id") Long parentId
    ) {
        parentService.delete(parentId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @GetMapping
    public ResponseEntity<ParentPage> findAll(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(parentService.findAll(page));
    }

    @GetMapping("/{parent-id}/children")
    public ResponseEntity<StudentPage> getChildren(
            @PathVariable("parent-id") Long parentId
    ) {
        return ResponseEntity
                .ok(parentService.getChildren(parentId));
    }

    @GetMapping("/{parent-id}/children/{child-id}")
    public ResponseEntity<StudentPage> addChild(
            @PathVariable("parent-id") Long parentId,
            @PathVariable("child-id") Long childId
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(parentService.addChild(parentId, childId));
    }
}
