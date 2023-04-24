package com.example.school.dto.timetable;

import com.example.school.dto.clazz.ClazzDto;
import com.example.school.dto.lesson.LessonDto;
import com.example.school.dto.teacher.TeacherDto;
import com.example.school.model.TimetableLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimetableLineDto {
    private Long id;
    private Date dateTime;
    private String roomNumber;
    private ClazzDto clazz;
    private TeacherDto teacher;
    private LessonDto lesson;

    public static TimetableLineDto from(TimetableLine timetableLine) {
        return TimetableLineDto.builder()
                .id(timetableLine.getId())
                .dateTime(timetableLine.getDateTime())
                .roomNumber(timetableLine.getRoomNumber())
                .clazz(ClazzDto.from(timetableLine.getClazz()))
                .teacher(TeacherDto.from(timetableLine.getTeacher()))
                .lesson(LessonDto.from(timetableLine.getLesson()))
                .build();
    }

    public static List<TimetableLineDto> from(List<TimetableLine> timetableLines) {
        return timetableLines.stream()
                .map(TimetableLineDto::from)
                .collect(Collectors.toList());
    }
}
