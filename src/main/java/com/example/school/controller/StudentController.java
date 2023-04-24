package com.example.school.controller;

import com.example.school.dto.student.NewOrUpdatedStudentDto;
import com.example.school.dto.student.StudentDto;
import com.example.school.dto.student.StudentPage;
import com.example.school.dto.task.TaskPage;
import com.example.school.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping("/{student-id}")
    public ResponseEntity<StudentDto> findById(
            @PathVariable("student-id") Long studentId
    ) {
        return ResponseEntity
                .ok(studentService.findDtoById(studentId));
    }

    @GetMapping
    public ResponseEntity<StudentPage> findAll(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(studentService.findAll(page));
    }

    @PostMapping
    public ResponseEntity<StudentDto> save(
            @RequestBody NewOrUpdatedStudentDto newStudentDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(studentService.save(newStudentDto));
    }

    @GetMapping("{student-id}/tasks")
    public ResponseEntity<TaskPage> getTasks(
            @PathVariable("student-id") Long studentId,
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(studentService.getTasks(studentId, page));
    }

    @PutMapping("/{student-id}")
    public ResponseEntity<StudentDto> update(
            @PathVariable("student-id") Long studentId,
            @RequestBody NewOrUpdatedStudentDto updatedStudentDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(studentService.update(studentId, updatedStudentDto));
    }

    @DeleteMapping("/{student-id}")
    public ResponseEntity<?> delete(
            @PathVariable("student-id") Long studentId
    ) {
        studentService.delete(studentId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}
