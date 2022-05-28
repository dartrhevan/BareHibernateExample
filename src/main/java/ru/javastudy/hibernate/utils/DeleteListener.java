package ru.javastudy.hibernate.utils;

import ru.javastudy.hibernate.entities.Student;

import javax.persistence.PreRemove;

public class DeleteListener {
    @PreRemove
    public void onDeleteStudent(Student st) {
        System.out.printf("Deleting %s%n", st);
    }
}
