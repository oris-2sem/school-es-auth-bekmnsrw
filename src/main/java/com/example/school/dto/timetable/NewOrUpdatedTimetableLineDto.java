package com.example.school.dto.timetable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedTimetableLineDto {
    private LocalDate date;
    private LocalTime time;
    private String roomNumber;
    private Long clazzId;
    private Long teacherId;
    private Long lessonId;
}
