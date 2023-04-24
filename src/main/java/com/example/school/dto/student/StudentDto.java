package com.example.school.dto.student;

import com.example.school.dto.task.TaskDto;
import com.example.school.dto.clazz.ClazzDto;
import com.example.school.dto.parent.ParentDto;
import com.example.school.model.Student;
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
public class StudentDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private ClazzDto clazz;
    private ParentDto parent;

    public static StudentDto from(Student student) {
        return StudentDto.builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .secondName(student.getSecondName())
                .patronymic(student.getPatronymic())
                .clazz(ClazzDto.from(student.getClazz()))
                .parent(ParentDto.from(student.getParent()))
                .build();
    }

    public static List<StudentDto> from(List<Student> students) {
        return students.stream()
                .map(StudentDto::from)
                .collect(Collectors.toList());
    }
}
