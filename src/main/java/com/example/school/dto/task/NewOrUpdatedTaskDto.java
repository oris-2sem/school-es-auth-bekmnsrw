package com.example.school.dto.task;

import com.example.school.model.util.Mark;
import com.example.school.model.util.TaskType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedTaskDto {
    private TaskType taskType;
    private String description;
    private Mark mark;
    private String commentary;
    private Long studentId;
    private Long lessonId;
}
