package com.example.school.service.impl;

import com.example.school.dto.timetable.TimetableLineDto;
import com.example.school.dto.timetable.TimetableLinePage;
import com.example.school.exception.NotFoundException;
import com.example.school.dto.teacher.NewOrUpdatedTeacherDto;
import com.example.school.dto.teacher.TeacherDto;
import com.example.school.dto.teacher.TeacherPage;
import com.example.school.model.Teacher;
import com.example.school.model.TimetableLine;
import com.example.school.repository.TeacherRepository;
import com.example.school.repository.TimetableLineRepository;
import com.example.school.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TimetableLineRepository timetableLineRepository;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public TeacherDto findDtoById(Long id) {
        return TeacherDto.from(getOrThrow(id));
    }

    @Override
    public Teacher findById(Long id) {
        return getOrThrow(id);
    }

    @Override
    public void delete(Long teacherId) {
        teacherRepository.delete(getOrThrow(teacherId));
    }

    @Override
    public TeacherPage findAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Teacher> teacherPage = teacherRepository.findAll(pageRequest);

        return TeacherPage.builder()
                .teachers(TeacherDto.from(teacherPage.getContent()))
                .totalPages(teacherPage.getTotalPages())
                .build();
    }

    @Override
    public TimetableLinePage findTimetableLines(Long teacherId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<TimetableLine> timetableLinePage = timetableLineRepository.findAllByTeacherId(teacherId, pageRequest);

        return TimetableLinePage.builder()
                .timetableLines(TimetableLineDto.from(timetableLinePage.getContent()))
                .totalPages(timetableLinePage.getTotalPages())
                .build();
    }

    @Override
    public TeacherDto save(NewOrUpdatedTeacherDto newTeacher) {
        Teacher teacher = Teacher.builder()
                .firstName(newTeacher.getFirstName())
                .secondName(newTeacher.getSecondName())
                .patronymic(newTeacher.getPatronymic())
                .teacherSpeciality(newTeacher.getTeacherSpeciality())
                .build();

        return TeacherDto.from(teacherRepository.save(teacher));
    }

    @Override
    public TeacherDto update(Long teacherId, NewOrUpdatedTeacherDto updatedTeacher) {
        Teacher teacher = getOrThrow(teacherId);

        teacher.setFirstName(updatedTeacher.getFirstName());
        teacher.setSecondName(updatedTeacher.getSecondName());
        teacher.setPatronymic(updatedTeacher.getPatronymic());
        teacher.setTeacherSpeciality(updatedTeacher.getTeacherSpeciality());

        return TeacherDto.from(teacherRepository.save(teacher));
    }

    private Teacher getOrThrow(Long id) {
        return teacherRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find teacher with id: " + id));
    }
}
