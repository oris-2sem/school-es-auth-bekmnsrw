package com.example.school.dto.timetable;

import com.example.school.dto.clazz.ClazzDto;
import com.example.school.dto.lesson.LessonDto;
import com.example.school.dto.teacher.TeacherDto;
import com.example.school.model.TimetableLine;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimetableLineDto {
    private Long id;
    private LocalDate date;
    private LocalTime time;
    private String roomNumber;
    private ClazzDto clazz;
    private TeacherDto teacher;
    private LessonDto lesson;

    public static TimetableLineDto from(TimetableLine timetableLine) {
        TimetableLineDto timetableLineDto = TimetableLineDto.builder()
                .id(timetableLine.getId())
                .date(timetableLine.getDate())
                .time(timetableLine.getTime())
                .roomNumber(timetableLine.getRoomNumber())
                .build();

        if (timetableLine.getClazz() != null) {
            timetableLineDto.setClazz(ClazzDto.from(timetableLine.getClazz()));
        }

        if (timetableLine.getTeacher() != null) {
            timetableLineDto.setTeacher(TeacherDto.from(timetableLine.getTeacher()));
        }

        if (timetableLine.getLesson() != null) {
            timetableLineDto.setLesson(LessonDto.from(timetableLine.getLesson()));
        }

        return timetableLineDto;
    }

    public static List<TimetableLineDto> from(List<TimetableLine> timetableLines) {
        return timetableLines.stream()
                .map(TimetableLineDto::from)
                .collect(Collectors.toList());
    }
}
