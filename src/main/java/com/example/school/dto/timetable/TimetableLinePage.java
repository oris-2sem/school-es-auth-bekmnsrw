package com.example.school.dto.timetable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TimetableLinePage {
    List<TimetableLineDto> timetableLines;
    private Integer totalPages;
}
