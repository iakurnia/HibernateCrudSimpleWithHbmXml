package util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public abstract class EntityBase {
    protected SessionFactory factory;
    protected Session session;
    protected Transaction transaction;

    public final void connect() {
        factory = new Configuration().configure().buildSessionFactory();
        session = factory.openSession();
        transaction = session.beginTransaction();
    }

    public final void disconect() {
        transaction.commit();
        session.close();
    }
}
