package com.example.school.dto.timetable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedTimetableLineDto {
    private Date dateTime;
    private String roomNumber;
    private Long clazzId;
    private Long teacherId;
    private Long lessonId;
}
