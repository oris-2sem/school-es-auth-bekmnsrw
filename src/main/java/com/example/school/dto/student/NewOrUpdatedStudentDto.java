package com.example.school.dto.student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedStudentDto {
    private String firstName;
    private String secondName;
    private String patronymic;
    private Long clazzId;
    private Long parentId;
}
