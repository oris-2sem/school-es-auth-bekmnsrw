package com.example.school.dto.lesson;

import com.example.school.model.util.LessonType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedLessonDto {
    private LessonType subject;
    private String theme;
}
