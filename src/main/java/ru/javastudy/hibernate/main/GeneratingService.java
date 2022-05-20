package ru.javastudy.hibernate.main;

import ru.javastudy.hibernate.dao.AbstractDAO;
import ru.javastudy.hibernate.dao.PersonDAO;
import ru.javastudy.hibernate.dao.RecordBookDAO;
import ru.javastudy.hibernate.dao.StudentDAO;
import ru.javastudy.hibernate.entities.Person;
import ru.javastudy.hibernate.entities.RecordBook;
import ru.javastudy.hibernate.entities.Student;
import ru.javastudy.hibernate.utils.EntityGenerator;
import ru.javastudy.hibernate.utils.HibernateSessionFactory;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GeneratingService {
    public void generateAndPersistEntities() {
        HibernateSessionFactory.doInTransaction(s -> {
            AbstractDAO<Student> studentDAO = new StudentDAO(s);
            AbstractDAO<Person> personDAO = new PersonDAO(s);
            AbstractDAO<RecordBook> recordBookDAO = new RecordBookDAO(s);

            int recordBooksCount = new Random().nextInt(5) + 5;
            List<Student> students = Stream.generate(EntityGenerator::generatePerson).limit(10)
                    .peek(personDAO::persist).map(EntityGenerator::generateStudent).collect(Collectors.toList());
            for (int i = 0; i < recordBooksCount; i++) {
                students.get(i).setRecordBook(EntityGenerator.generateRecordBook());
                recordBookDAO.persist(students.get(0).getRecordBook());
            }
            students.forEach(studentDAO::persist);
        });
    }


    public void printAllEntities() {
        HibernateSessionFactory.doInTransaction(s -> {
            AbstractDAO<Student> studentDAO = new StudentDAO(s);
            AbstractDAO<Person> personDAO = new PersonDAO(s);
            AbstractDAO<RecordBook> recordBookDAO = new RecordBookDAO(s);

            System.out.println("All students:");
            studentDAO.findAll().forEach(System.out::println);
            System.out.println();

            System.out.println("All persons:");
            personDAO.findAll().forEach(System.out::println);
            System.out.println();

            System.out.println("All record books:");
            recordBookDAO.findAll().forEach(System.out::println);
            System.out.println();
        });
    }
}
