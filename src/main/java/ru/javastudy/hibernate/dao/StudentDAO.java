package ru.javastudy.hibernate.dao;

import ru.javastudy.hibernate.entities.Student;

import java.util.List;

public interface StudentDAO {
    List<Student> getStudentsBySubstring(String substr);

    List<Student> getStudentsWithRecordBook();

    void persist(Student person);

    List<Student> findAll();
}
