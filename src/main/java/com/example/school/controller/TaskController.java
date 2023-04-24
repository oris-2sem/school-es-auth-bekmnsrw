package com.example.school.controller;

import com.example.school.dto.task.NewOrUpdatedTaskDto;
import com.example.school.dto.task.TaskDto;
import com.example.school.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/{task-id}")
    public ResponseEntity<TaskDto> findById(
            @PathVariable("task-id") Long taskId
    ) {
        return ResponseEntity
                .ok(taskService.findDtoById(taskId));
    }

    @PostMapping
    public ResponseEntity<TaskDto> save(
            @RequestBody NewOrUpdatedTaskDto newTaskDto
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(taskService.save(newTaskDto));
    }

    @PutMapping("/{task-id}")
    public ResponseEntity<TaskDto> update(
            @PathVariable("task-id") Long taskId,
            @RequestBody NewOrUpdatedTaskDto updatedTaskDto
    ) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(taskService.update(taskId, updatedTaskDto));
    }

    @DeleteMapping("/{task-id}")
    public ResponseEntity<?> delete(
            @PathVariable("task-id") Long taskId
    ) {
        taskService.delete(taskId);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .build();
    }
}
