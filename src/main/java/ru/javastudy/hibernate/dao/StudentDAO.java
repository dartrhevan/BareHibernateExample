package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import ru.javastudy.hibernate.entities.Student;

public class StudentDAO extends AbstractDAO<Student> {
    public StudentDAO(Session session) {
        super(session);
    }

    @Override
    public String getEntityName() {
        return "Student";
    }
}
