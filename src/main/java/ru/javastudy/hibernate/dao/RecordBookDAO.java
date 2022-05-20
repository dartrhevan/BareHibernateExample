package ru.javastudy.hibernate.dao;

import org.hibernate.Session;
import ru.javastudy.hibernate.entities.RecordBook;

public class RecordBookDAO extends AbstractDAO<RecordBook> {
    public RecordBookDAO(Session session) {
        super(session);
    }

    @Override
    public String getEntityName() {
        return "RecordBook";
    }
}
