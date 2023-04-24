package com.example.school.service;

import com.example.school.dto.timetable.NewOrUpdatedTimetableLineDto;
import com.example.school.dto.timetable.TimetableLineDto;
import com.example.school.dto.timetable.TimetableLinePage;

public interface TimetableLineService {

    TimetableLineDto save(NewOrUpdatedTimetableLineDto newTimetableLineDto);
    TimetableLinePage findAll(Integer page);
}
