package com.example.school.service;

import com.example.school.dto.task.NewOrUpdatedTaskDto;
import com.example.school.dto.task.TaskDto;
import com.example.school.model.Task;

public interface TaskService {

    TaskDto save(NewOrUpdatedTaskDto newTaskDto);
    TaskDto update(Long taskId, NewOrUpdatedTaskDto updatedTaskDto);
    TaskDto findDtoById(Long taskId);
    Task findById(Long taskId);
    void delete(Long taskId);
}
