package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import ru.javastudy.hibernate.entities.Student;

import java.util.List;

public class StudentHQLDAO extends AbstractDAO<Student> implements StudentDAO {
    public StudentHQLDAO(Session session) {
        super(session);
    }

    @Override
    public List<Student> getStudentsBySubstring(String substr) {
        return session.createQuery("SELECT s FROM Student s WHERE " +
                "s.person.lastName like :p OR s.person.firstName like :p OR s.person.middleName like :p")
                .setParameter("p", "%" + substr + "%")
                .list();
    }

    @Override
    public List<Student> getStudentsWithRecordBook() {
        return session.createQuery("SELECT s FROM Student s WHERE s.recordBook is not null").list();
    }

    @Override
    public void deleteAll() {
        findAll().forEach(session::remove);
    }

    @Override
    public String getEntityName() {
        return "Student";
    }
}
