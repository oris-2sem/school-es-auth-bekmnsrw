package com.example.school.dto.lesson;

import com.example.school.dto.task.TaskDto;
import com.example.school.dto.timetable.TimetableLineDto;
import com.example.school.model.Lesson;
import com.example.school.model.util.LessonType;
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
public class LessonDto {
    private Long id;
    private LessonType subject;
    private String theme;

    public static LessonDto from(Lesson lesson) {
        return LessonDto.builder()
                .id(lesson.getId())
                .subject(lesson.getSubject())
                .theme(lesson.getTheme())
                .build();
    }

    public static List<LessonDto> from(List<Lesson> lessons) {
        return lessons.stream()
                .map(LessonDto::from)
                .collect(Collectors.toList());
    }
}
