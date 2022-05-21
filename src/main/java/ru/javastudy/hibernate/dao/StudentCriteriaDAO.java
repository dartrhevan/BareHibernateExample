package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import ru.javastudy.hibernate.entities.Person;
import ru.javastudy.hibernate.entities.Person_;
import ru.javastudy.hibernate.entities.Student;
import ru.javastudy.hibernate.entities.Student_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

public class StudentCriteriaDAO extends AbstractDAO<Student> implements StudentDAO {
    public StudentCriteriaDAO(Session session) {
        super(session);
    }

    @Override
    public List<Student> getStudentsBySubstring(String substr) {
        String pattern = "%" + substr + "%";
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = b.createQuery(Student.class);
        Root<Student> student = criteria.from(Student.class);
        Join<Student, Person> person = student.join(Student_.person);
        return session.createQuery(criteria.select(student).where(b.or(b.like(
                person.get(Person_.lastName), pattern),
                b.like(person.get(Person_.firstName), pattern),
                b.like(person.get(Person_.middleName), pattern)))).list();

    }

    @Override
    public List<Student> getStudentsWithRecordBook() {
        CriteriaBuilder b = session.getCriteriaBuilder();
        CriteriaQuery<Student> criteria = b.createQuery(Student.class);
        Root<Student> student = criteria.from(Student.class);
        return session.createQuery(criteria.select(student).where(b.isNotNull(student.get(Student_.recordBook)))).list();

    }

    @Override
    public String getEntityName() {
        return "Student";
    }
}
