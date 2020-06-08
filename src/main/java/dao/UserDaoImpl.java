package dao;

import java.util.List;
import model.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(user);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new HibernateException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<User> listUsers() {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(
                    "from User ", User.class);
            return query.list();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }

    @Override
    public User getUserById(Long userId) {
        try (Session session = sessionFactory.openSession()) {
            Query<User> query = session.createQuery(
                    "from User where userId = :userId", User.class);
            query.setParameter("userId", userId);
            return query.uniqueResult();
        } catch (HibernateException e) {
            throw new HibernateException(e);
        }
    }
}
