package com.example.school.dto.task;

import com.example.school.dto.lesson.LessonDto;
import com.example.school.dto.student.StudentDto;
import com.example.school.model.Task;
import com.example.school.model.util.Mark;
import com.example.school.model.util.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskDto {
    private Long id;
    private TaskType taskType;
    private String description;
    private Mark mark;
    private String commentary;
    private StudentDto student;
    private LessonDto lesson;

    public static TaskDto from(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .taskType(task.getTaskType())
                .description(task.getDescription())
                .mark(task.getMark())
                .commentary(task.getCommentary())
                .build();

        if (task.getStudent() != null) {
            taskDto.setStudent(StudentDto.from(task.getStudent()));
        }

        if (task.getLesson() != null) {
            taskDto.setLesson(LessonDto.from(task.getLesson()));
        }

        return taskDto;
    }

    public static List<TaskDto> from(List<Task> tasks) {
        return tasks.stream()
                .map(TaskDto::from)
                .collect(Collectors.toList());
    }
}
