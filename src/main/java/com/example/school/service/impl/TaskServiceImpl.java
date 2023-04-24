package com.example.school.service.impl;

import com.example.school.dto.task.NewOrUpdatedTaskDto;
import com.example.school.dto.task.TaskDto;
import com.example.school.exception.NotFoundException;
import com.example.school.model.Task;
import com.example.school.repository.TaskRepository;
import com.example.school.service.LessonService;
import com.example.school.service.StudentService;
import com.example.school.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final LessonService lessonService;
    private final StudentService studentService;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public TaskDto save(NewOrUpdatedTaskDto newTaskDto) {
        Task task = Task.builder()
                .taskType(newTaskDto.getTaskType())
                .description(newTaskDto.getDescription())
                .mark(newTaskDto.getMark())
                .commentary(newTaskDto.getCommentary())
                .build();

        if (newTaskDto.getStudentId() != null) {
            task.setStudent(studentService.findById(newTaskDto.getStudentId()));
        }

        if (newTaskDto.getLessonId() != null) {
            task.setLesson(lessonService.findById(newTaskDto.getLessonId()));
        }

        return TaskDto.from(taskRepository.save(task));
    }

    @Override
    public TaskDto update(Long taskId, NewOrUpdatedTaskDto updatedTaskDto) {
        Task task = getOrThrow(taskId);

        task.setTaskType(updatedTaskDto.getTaskType());
        task.setDescription(updatedTaskDto.getDescription());
        task.setMark(updatedTaskDto.getMark());
        task.setCommentary(updatedTaskDto.getCommentary());

        if (updatedTaskDto.getLessonId() != null) {
            task.setLesson(lessonService.findById(updatedTaskDto.getLessonId()));
        }

        if (updatedTaskDto.getStudentId() != null) {
            task.setStudent(studentService.findById(updatedTaskDto.getStudentId()));
        }

        return TaskDto.from(taskRepository.save(task));
    }

    @Override
    public TaskDto findDtoById(Long taskId) {
        return TaskDto.from(getOrThrow(taskId));
    }

    @Override
    public Task findById(Long taskId) {
        return getOrThrow(taskId);
    }

    @Override
    public void delete(Long taskId) {
        taskRepository.delete(getOrThrow(taskId));
    }

    private Task getOrThrow(Long id) {
        return taskRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find task with id: " + id));
    }
}
