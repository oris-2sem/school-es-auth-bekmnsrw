package com.example.school.dto.parent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NewOrUpdatedParentDto {
    private String firstName;
    private String secondName;
    private String patronymic;
}
