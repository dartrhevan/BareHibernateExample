//package ru.javastudy.hibernate.utils;
//
//import org.hibernate.EmptyInterceptor;
//import org.hibernate.HibernateException;
//import org.hibernate.event.spi.DeleteEvent;
//import org.hibernate.event.spi.DeleteEventListener;
//import org.hibernate.event.spi.PostDeleteEvent;
//import org.hibernate.event.spi.PostDeleteEventListener;
//import org.hibernate.persister.entity.EntityPersister;
//import org.hibernate.type.Type;
//
//import java.io.Serializable;
//import java.util.Set;
//
//public class LogInterceptor extends EmptyInterceptor implements PostDeleteEventListener {//TODO: set somewhere
//    @Override
//    public void onDelete(Object entity,
//                         Serializable id,
//                         Object[] state,
//                         String[] propertyNames,
//                         Type[] types) {
//        // do nothing
//        System.out.printf("Deleting '%s'%n", entity);
//    }
////
////    @Override
////    public void onDelete(DeleteEvent deleteEvent) throws HibernateException {
////        System.out.printf("Deleting '%s'%n", deleteEvent.getObject());
////    }
////
////    @Override
////    public void onDelete(DeleteEvent deleteEvent, Set set) throws HibernateException {
////        System.out.printf("Deleting '%s'%n", deleteEvent.getObject());
////    }
//
//    @Override
//    public void onPostDelete(PostDeleteEvent postDeleteEvent) {
//        System.out.printf("Deleting '%s'%n", postDeleteEvent.getEntity());
//    }
//
//    @Override
//    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
//        return true;
//    }
//
//    @Override
//    public boolean requiresPostCommitHandling(EntityPersister persister) {
//        return true; //PostDeleteEventListener.super.requiresPostCommitHandling(persister);
//    }
//}