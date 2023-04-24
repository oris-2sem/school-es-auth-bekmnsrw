package com.example.school.controller;

import com.example.school.dto.teacher.NewOrUpdatedTeacherDto;
import com.example.school.dto.teacher.TeacherDto;
import com.example.school.dto.teacher.TeacherPage;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/teachers")
public class TeacherController {

    private final TeacherService teacherService;

    @PostMapping
    public ResponseEntity<TeacherDto> save(
            @RequestBody NewOrUpdatedTeacherDto newTeacherDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(teacherService.save(newTeacherDto));
    }

    @GetMapping("/{teacher-id}")
    public ResponseEntity<TeacherDto> getById(
            @PathVariable("teacher-id") Long teacherId
    ) {
        return ResponseEntity
                .ok(teacherService.findDtoById(teacherId));
    }

    @PutMapping("/{teacher-id}")
    public ResponseEntity<TeacherDto> update(
            @PathVariable("teacher-id") Long teacherId,
            @RequestBody NewOrUpdatedTeacherDto updatedTeacherDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(teacherService.update(teacherId, updatedTeacherDto));
    }

    @DeleteMapping("/{teacher-id}")
    public ResponseEntity<?> delete(
            @PathVariable("teacher-id") Long teacherId
    ) {
        teacherService.delete(teacherId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @GetMapping
    public ResponseEntity<TeacherPage> findAll(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(teacherService.findAll(page));
    }
}
