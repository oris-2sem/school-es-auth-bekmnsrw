package com.example.school.dto.clazz;

import com.example.school.dto.teacher.TeacherDto;
import com.example.school.model.Clazz;
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
public class ClazzDto {
    private Long id;
    private Long yearOfBeginningOfStudying;
    private String litera;
    private TeacherDto teacher;

    public static ClazzDto from(Clazz clazz) {
        ClazzDto clazzDto = ClazzDto.builder()
                .id(clazz.getId())
                .yearOfBeginningOfStudying(clazz.getYearOfBeginningOfStudying())
                .litera(clazz.getLitera())
                .build();

        if (clazz.getTeacher() != null) {
            clazzDto.setTeacher(TeacherDto.from(clazz.getTeacher()));
        }

        return clazzDto;
    }

    public static List<ClazzDto> from(List<Clazz> clazzes) {
        return clazzes.stream()
                .map(ClazzDto::from)
                .collect(Collectors.toList());
    }
}
