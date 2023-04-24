package com.example.school.model.util;

public enum Mark {
    EXCELLENT(5),
    GOOD(4),
    SATISFACTORY(3),
    BAD(2),
    UNSATISFACTORY(1);

    public final Integer value;

    Mark(Integer value) {
        this.value = value;
    }
}
