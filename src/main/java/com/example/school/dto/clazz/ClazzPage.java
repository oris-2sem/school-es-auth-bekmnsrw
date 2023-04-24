package com.example.school.dto.clazz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClazzPage {
    private List<ClazzDto> clazzes;
    private Integer totalPages;
}
