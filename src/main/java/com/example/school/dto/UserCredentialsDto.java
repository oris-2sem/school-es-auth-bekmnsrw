package com.example.school.dto;

import com.example.school.model.UserCredentials;
import com.example.school.model.util.Role;
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
public class UserCredentialsDto {
    private String login;
    private Role role;

    public static UserCredentialsDto from(UserCredentials userCredentials) {
        return UserCredentialsDto.builder()
                .login(userCredentials.getLogin())
                .role(userCredentials.getRole())
                .build();
    }

    public static List<UserCredentialsDto> from(List<UserCredentials> userCredentialsList) {
        return userCredentialsList.stream()
                .map(UserCredentialsDto::from)
                .collect(Collectors.toList());
    }
}
