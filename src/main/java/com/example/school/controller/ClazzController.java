package com.example.school.controller;

import com.example.school.dto.clazz.ClazzDto;
import com.example.school.dto.clazz.NewOrUpdatedClazzDto;
import com.example.school.dto.student.StudentPage;
import com.example.school.service.ClazzService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/clazzes")
public class ClazzController {

    private final ClazzService clazzService;

    @PostMapping
    public ResponseEntity<ClazzDto> save(
            @RequestBody NewOrUpdatedClazzDto newClazzDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clazzService.save(newClazzDto));
    }

    @GetMapping("/{clazz-id}")
    public ResponseEntity<ClazzDto> findById(
            @PathVariable("clazz-id") Long clazzId
    ) {
        return ResponseEntity
                .ok(clazzService.findDtoById(clazzId));
    }

    @PutMapping("/{clazz-id}")
    public ResponseEntity<ClazzDto> update(
            @PathVariable("clazz-id") Long clazzId,
            @RequestBody NewOrUpdatedClazzDto updatedClazzDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(clazzService.update(clazzId, updatedClazzDto));
    }

    @DeleteMapping("/{clazz-id}")
    public ResponseEntity<?> delete(
            @PathVariable("clazz-id") Long clazzId
    ) {
        clazzService.delete(clazzId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @PostMapping("/{clazz-id}/students/{student-id}")
    public ResponseEntity<StudentPage> addStudent(
            @PathVariable("clazz-id") Long clazzId,
            @PathVariable("student-id") Long studentId
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(clazzService.addStudent(clazzId, studentId));
    }

    @GetMapping("/{clazz-id}/students")
    public ResponseEntity<StudentPage> getStudentsFromClazz(
            @PathVariable("clazz-id") Long clazzId
    ) {
        return ResponseEntity
                .ok(clazzService.getStudentsFromClazz(clazzId));
    }
}
