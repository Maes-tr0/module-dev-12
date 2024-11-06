package ua.maestr0.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.maestr0.util.database.HibernateUtil;

public abstract class GenericDAO<T, K> {
    private static final Logger logger = LoggerFactory.getLogger(GenericDAO.class);
    private final SessionFactory sessionFactory;
    private final Class<T> entityClass;

    protected GenericDAO(Class<T> entityClass) {
        this.sessionFactory = HibernateUtil.getInstance().getSessionFactory();
        this.entityClass = entityClass;
    }

    public void save(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(entity);
            tx.commit();
            logger.info("Entity saved successfully: {}", entity);
        } catch (Exception e) {
            if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE) {
                tx.rollback();
            }
            logger.error("Error saving entity: {}", entity, e);
        }
    }

    public void update(T entity) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.merge(entity);
            tx.commit();
            logger.info("Entity updated successfully: {}", entity);
        } catch (Exception e) {
            if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE) {
                tx.rollback();
            }
            logger.error("Error updating entity: {}", entity, e);
        }
    }

    public void delete(K key) {
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            T entity = session.get(entityClass, key);
            if (entity != null) {
                session.remove(entity);
                tx.commit();
                logger.info("Entity deleted successfully: {}", entity);
            } else {
                tx.rollback();
                logger.warn("Entity not found for deletion with key: {}", key);
            }
        } catch (Exception e) {
            if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE) {
                tx.rollback();
            }
            logger.error("Error deleting entity with key: {}", key, e);
        }
    }

    public T get(K id) {
        Transaction tx = null;
        T entity = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            entity = session.get(entityClass, id);
            tx.commit();
            if (entity != null) {
                logger.info("Entity retrieved successfully: {}", entity);
            } else {
                logger.warn("No entity found with id: {}", id);
            }
        } catch (Exception e) {
            if (tx != null && tx.getStatus() == TransactionStatus.ACTIVE) {
                tx.rollback();
            }
            logger.error("Error getting entity with id: {}", id, e);
        }
        return entity;
    }
}
