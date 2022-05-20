package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import ru.javastudy.hibernate.entities.Person;

public class PersonDAO extends AbstractDAO<Person> {
    public PersonDAO(Session session) {
        super(session);
    }

    @Override
    public String getEntityName() {
        return "Person";
    }
}
