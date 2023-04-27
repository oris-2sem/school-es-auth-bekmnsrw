package com.example.school.dto.parent;

import com.example.school.dto.UserCredentialsDto;
import com.example.school.model.Parent;
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
public class ParentDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String patronymic;
    private UserCredentialsDto userCredentialsDto;

    public static ParentDto from(Parent parent) {
        return ParentDto.builder()
                .id(parent.getId())
                .firstName(parent.getFirstName())
                .secondName(parent.getSecondName())
                .patronymic(parent.getPatronymic())
                .userCredentialsDto(UserCredentialsDto.from(parent.getUserCredentials()))
                .build();
    }

    public static List<ParentDto> from(List<Parent> parents) {
        return parents.stream()
                .map(ParentDto::from)
                .collect(Collectors.toList());
    }
}
