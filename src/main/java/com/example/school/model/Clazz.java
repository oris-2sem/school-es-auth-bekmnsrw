package com.example.school.model;

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
@Entity(name = "clazz")
public class Clazz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // How to calculate current group's number?
    // currentGroupNumber = yearOfBeginningOfStudying - currentYear + 1
    @Column(name = "year_of_beginning_of_studying")
    private Long yearOfBeginningOfStudying;

    private String litera;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "clazz")
    private List<Student> students;

    @OneToMany(mappedBy = "clazz")
    private List<TimetableLine> timetableLines;
}
