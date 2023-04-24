package com.example.school.service.impl;

import com.example.school.dto.lesson.LessonDto;
import com.example.school.dto.lesson.LessonPage;
import com.example.school.dto.lesson.NewOrUpdatedLessonDto;
import com.example.school.dto.task.TaskDto;
import com.example.school.dto.task.TaskPage;
import com.example.school.dto.timetable.TimetableLineDto;
import com.example.school.dto.timetable.TimetableLinePage;
import com.example.school.exception.NotFoundException;
import com.example.school.model.Lesson;
import com.example.school.model.Task;
import com.example.school.model.TimetableLine;
import com.example.school.repository.LessonRepository;
import com.example.school.repository.TaskRepository;
import com.example.school.repository.TimetableLineRepository;
import com.example.school.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {

    private final LessonRepository lessonRepository;
    private final TaskRepository taskRepository;
    private final TimetableLineRepository timetableLineRepository;

    @Value("${application.default-page-size}")
    private Integer defaultPageSize;

    @Override
    public LessonDto save(NewOrUpdatedLessonDto newLessonDto) {
        Lesson lesson = Lesson.builder()
                .subject(newLessonDto.getSubject())
                .theme(newLessonDto.getTheme())
                .build();

        return LessonDto.from(lessonRepository.save(lesson));
    }

    @Override
    public LessonDto update(Long lessonId, NewOrUpdatedLessonDto updatedLessonDto) {
        Lesson lesson = getOrThrow(lessonId);

        lesson.setSubject(updatedLessonDto.getSubject());
        lesson.setTheme(updatedLessonDto.getTheme());

        return LessonDto.from(lessonRepository.save(lesson));
    }

    @Override
    public void delete(Long lessonId) {
        lessonRepository.delete(getOrThrow(lessonId));
    }

    @Override
    public Lesson findById(Long lessonId) {
        return getOrThrow(lessonId);
    }

    @Override
    public LessonDto findDtoById(Long lessonId) {
        return LessonDto.from(getOrThrow(lessonId));
    }

    @Override
    public LessonPage findAll(Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Lesson> lessonPage = lessonRepository.findAll(pageRequest);

        return LessonPage.builder()
                .lessons(LessonDto.from(lessonPage.getContent()))
                .totalPages(lessonPage.getTotalPages())
                .build();
    }

    @Override
    public TaskPage getTasks(Long lessonId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<Task> taskPage = taskRepository.findAllByLessonId(lessonId, pageRequest);

        return TaskPage.builder()
                .tasks(TaskDto.from(taskPage.getContent()))
                .totalPages(taskPage.getTotalPages())
                .build();
    }

    @Override
    public TimetableLinePage getTimetableLines(Long lessonId, Integer page) {
        PageRequest pageRequest = PageRequest.of(page, defaultPageSize);
        Page<TimetableLine> timetableLinePage = timetableLineRepository.findAllByLessonId(lessonId, pageRequest);

        return TimetableLinePage.builder()
                .timetableLines(TimetableLineDto.from(timetableLinePage.getContent()))
                .totalPages(timetableLinePage.getTotalPages())
                .build();
    }

    private Lesson getOrThrow(Long id) {
        return lessonRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("Can't find lesson with id: " + id));
    }
}
