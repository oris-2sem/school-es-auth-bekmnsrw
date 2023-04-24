package com.example.school.service.impl;

import com.example.school.dto.timetable.NewOrUpdatedTimetableLineDto;
import com.example.school.dto.timetable.TimetableLineDto;
import com.example.school.dto.timetable.TimetableLinePage;
import com.example.school.model.TimetableLine;
import com.example.school.repository.TimetableLineRepository;
import com.example.school.service.ClazzService;
import com.example.school.service.LessonService;
import com.example.school.service.TeacherService;
import com.example.school.service.TimetableLineService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TimetableLineServiceImpl implements TimetableLineService {

    private final TimetableLineRepository timetableLineRepository;
    private final ClazzService clazzService;
    private final TeacherService teacherService;
    private final LessonService lessonService;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public TimetableLineDto save(NewOrUpdatedTimetableLineDto newTimetableLineDto) {
        TimetableLine timetableLine = TimetableLine.builder()
                .dateTime(newTimetableLineDto.getDateTime())
                .roomNumber(newTimetableLineDto.getRoomNumber())
                .build();

        if (newTimetableLineDto.getClazzId() != null) {
            timetableLine.setClazz(clazzService.findById(newTimetableLineDto.getClazzId()));
        }

        if (newTimetableLineDto.getTeacherId() != null) {
            timetableLine.setTeacher(teacherService.findById(newTimetableLineDto.getTeacherId()));
        }

        if (newTimetableLineDto.getLessonId() != null) {
            timetableLine.setLesson(lessonService.findById(newTimetableLineDto.getLessonId()));
        }

        return TimetableLineDto.from(timetableLineRepository.save(timetableLine));
    }

    @Override
    public TimetableLinePage findAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<TimetableLine> timetableLinePage = timetableLineRepository.findAll(pageRequest);

        return TimetableLinePage.builder()
                .timetableLines(TimetableLineDto.from(timetableLinePage.getContent()))
                .totalPages(timetableLinePage.getTotalPages())
                .build();
    }
}
