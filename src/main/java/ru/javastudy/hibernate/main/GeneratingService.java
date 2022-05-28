package ru.javastudy.hibernate.main;

import ru.javastudy.hibernate.dao.*;
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
            AbstractDAO<Student> studentDAO = new StudentHQLDAO(s);
            AbstractDAO<Person> personDAO = new PersonDAO(s);
            AbstractDAO<RecordBook> recordBookDAO = new RecordBookDAO(s);

            int recordBooksCount = new Random().nextInt(7) + 3;
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
        printEntities();
        printStudentsWithA();
        printStudentsWithRecordBook();
        deleteAll();
        HibernateSessionFactory.shutdown();
    }

    private void deleteAll() {
        System.out.println("Delete all");
        HibernateSessionFactory.doInTransaction(s -> {
            StudentDAO studentDAO = new StudentCriteriaDAO(s);
            studentDAO.deleteAll();
        });
    }

    private void printStudentsWithRecordBook() {
        HibernateSessionFactory.doInTransaction(s -> {
            StudentDAO studentHQLDAO = new StudentHQLDAO(s);
            StudentDAO studentCriteriaDAO = new StudentCriteriaDAO(s);
            System.out.println("Students with record book via HQL");
            studentHQLDAO.getStudentsWithRecordBook().forEach(System.out::println);
            System.out.println();

            System.out.println("Students with record book via Criteria");
            studentCriteriaDAO.getStudentsWithRecordBook().forEach(System.out::println);
            System.out.println();
        });
    }

    private void printStudentsWithA() {
        HibernateSessionFactory.doInTransaction(s -> {
            StudentDAO studentHQLDAO = new StudentHQLDAO(s);
            StudentDAO studentCriteriaDAO = new StudentCriteriaDAO(s);
            System.out.println("Students with 'a' letter via HQL");
            studentHQLDAO.getStudentsBySubstring("а").forEach(System.out::println);
            System.out.println();

            System.out.println("Students with 'a' letter via Criteria");
            studentCriteriaDAO.getStudentsBySubstring("а").forEach(System.out::println);
            System.out.println();
        });
    }

    private void printEntities() {
        HibernateSessionFactory.doInTransaction(s -> {
            StudentDAO studentHQLDAO = new StudentHQLDAO(s);
            AbstractDAO<Person> personDAO = new PersonDAO(s);
            AbstractDAO<RecordBook> recordBookDAO = new RecordBookDAO(s);

            System.out.println("All students:");
            studentHQLDAO.findAll().forEach(System.out::println);
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
