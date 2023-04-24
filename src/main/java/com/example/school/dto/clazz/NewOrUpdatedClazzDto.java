package com.example.school.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedClazzDto {
    private Long yearOfBeginningOfStudying;
    private String litera;
    private Long teacherId;
}
