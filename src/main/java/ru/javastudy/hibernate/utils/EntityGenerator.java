package ru.javastudy.hibernate.utils;

import ru.javastudy.hibernate.entities.Person;
import ru.javastudy.hibernate.entities.RecordBook;
import ru.javastudy.hibernate.entities.Student;

import java.util.Random;
import java.util.UUID;

public final class EntityGenerator {
    private EntityGenerator() {}

    private static Random random = new Random();

    public static Student generateStudent(Person person) {
        Student student = new Student();
        student.setPerson(person);
        student.setGroup(UUID.randomUUID().toString());

        return student;
    }

    public static Person generatePerson() {
        Person person = new Person();
        person.setFirstName(generateString());
        person.setLastName(generateString());
        person.setMiddleName(generateString());
        person.setPassportSeria(random.nextInt());

        return person;
    }

    public static RecordBook generateRecordBook() {
        RecordBook recordBook = new RecordBook();
        recordBook.setCode(random.nextInt());
        return recordBook;
    }

    public static String generateString() {
        return generateString(15);
    }

    public static String generateString(int targetStringLength) {
        int leftLimit = 0x0410; // letter 'А'
        int rightLimit = 0x044F; // letter 'я'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        return buffer.toString();
    }
}
