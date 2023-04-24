package com.example.school.controller;

import com.example.school.dto.lesson.LessonDto;
import com.example.school.dto.lesson.LessonPage;
import com.example.school.dto.lesson.NewOrUpdatedLessonDto;
import com.example.school.dto.task.TaskPage;
import com.example.school.dto.timetable.TimetableLinePage;
import com.example.school.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonService lessonService;

    @GetMapping
    public ResponseEntity<LessonPage> findAll(
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(lessonService.findAll(page));
    }

    @GetMapping("/{lesson-id}")
    public ResponseEntity<LessonDto> findById(
            @PathVariable("lesson-id") Long lessonId
    ) {
        return ResponseEntity
                .ok(lessonService.findDtoById(lessonId));
    }

    @PostMapping
    public ResponseEntity<LessonDto> save(
            @RequestBody NewOrUpdatedLessonDto newLessonDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(lessonService.save(newLessonDto));
    }

    @PutMapping("/{lesson-id}")
    public ResponseEntity<LessonDto> update(
            @PathVariable("lesson-id") Long lessonId,
            @RequestBody NewOrUpdatedLessonDto updatedLessonDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(lessonService.update(lessonId, updatedLessonDto));
    }

    @DeleteMapping("/{lesson-id}")
    public ResponseEntity<?> delete(
            @PathVariable("lesson-id") Long lessonId
    ) {
        lessonService.delete(lessonId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }

    @GetMapping("/{lesson-id}/tasks")
    public ResponseEntity<TaskPage> getTasks(
            @PathVariable("lesson-id") Long lessonId,
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(lessonService.getTasks(lessonId, page));
    }

    @GetMapping("/{lesson-id}/timetables")
    public ResponseEntity<TimetableLinePage> getTimetableLines(
            @PathVariable("lesson-id") Long lessonId,
            @RequestParam("page") Integer page
    ) {
        return ResponseEntity
                .ok(lessonService.getTimetableLines(lessonId, page));
    }
}
