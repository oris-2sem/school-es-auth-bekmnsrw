package com.example.school.model;

import com.example.school.model.util.TeacherSpeciality;
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
@Entity(name = "teacher")
public class Teacher {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private String patronymic;

    @Enumerated(EnumType.STRING)
    @Column(name = "speciality")
    private TeacherSpeciality teacherSpeciality;

    @OneToMany(mappedBy = "teacher")
    private List<TimetableLine> timetableLines;
}
