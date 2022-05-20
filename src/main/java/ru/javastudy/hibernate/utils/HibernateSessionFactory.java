package ru.javastudy.hibernate.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by Nick on 05.09.2015.
 */
public final class HibernateSessionFactory {

    private static SessionFactory sessionFactory = buildSessionFactory();

    private HibernateSessionFactory() {
    }

    public static void doInTransaction(final Consumer<Session> action) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        action.accept(session);

        session.getTransaction().commit();
        session.close();
    }

    public static<T> T doInTransaction(final Function<Session, T> action) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        T result = action.apply(session);

        session.getTransaction().commit();
        return result;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }

    private static SessionFactory buildSessionFactory() {
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            throw new ExceptionInInitializerError("Initial SessionFactory failed" + e);
        }
        return sessionFactory;
    }
}
