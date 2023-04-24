package com.example.school.model;

import com.example.school.model.util.LessonType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "lesson")
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private LessonType subject;

    private String theme;

    @OneToMany(mappedBy = "lesson")
    private List<TimetableLine> timetableLines;

    @OneToMany(mappedBy = "lesson")
    private List<Task> tasks;
}
