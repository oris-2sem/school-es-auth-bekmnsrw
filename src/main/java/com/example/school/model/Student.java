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
@Entity(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private String patronymic;

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    private Clazz clazz;

    @OneToMany(mappedBy = "student")
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Parent parent;
}
