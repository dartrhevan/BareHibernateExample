package ru.javastudy.hibernate.dao;

import org.hibernate.Session;

import java.util.List;

public abstract class AbstractDAO<T> {
    private final Session session;

    public AbstractDAO(Session session) {
        this.session = session;
    }

    public void persist(T person) {
        session.save(person);
    }

    public List<T> findAll() {
        return session.createQuery(String.format("SELECT e FROM %s e", getEntityName())).list();
    }

    abstract String getEntityName();
}
