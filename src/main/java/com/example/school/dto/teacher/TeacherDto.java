package com.example.school.dto.teacher;

import com.example.school.dto.timetable.TimetableLineDto;
import com.example.school.model.Teacher;
import com.example.school.model.util.TeacherSpeciality;
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
public class TeacherDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private TeacherSpeciality teacherSpeciality;

    public static TeacherDto from(Teacher teacher) {
        return TeacherDto.builder()
                .id(teacher.getId())
                .firstName(teacher.getFirstName())
                .secondName(teacher.getSecondName())
                .patronymic(teacher.getPatronymic())
                .teacherSpeciality(teacher.getTeacherSpeciality())
                .build();
    }

    public static List<TeacherDto> from(List<Teacher> teachers) {
        return teachers.stream()
                .map(TeacherDto::from)
                .collect(Collectors.toList());
    }
}
