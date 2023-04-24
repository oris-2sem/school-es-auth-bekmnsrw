package com.example.school.dto.teacher;

import com.example.school.model.util.TeacherSpeciality;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedTeacherDto {
    private String firstName;
    private String secondName;
    private String patronymic;
    private TeacherSpeciality teacherSpeciality;
}
