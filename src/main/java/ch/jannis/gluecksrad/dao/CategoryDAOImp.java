package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Category;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDAOImp implements CategoryDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Category> getCategoriesList() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Category> query = session.createQuery("FROM Category", Category.class);
        List<Category> list = query.getResultList();
        for (Category category : list
        ) {
            Hibernate.initialize(category.getQuestions());
            Hibernate.initialize(category.getWords());
        }
        transaction.commit();
        return list;
    }

    @Override
    @Transactional
    public boolean saveCategory(Category category) {
        boolean status = false;
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Category merged = session.merge(category);
            session.persist(merged);
            transaction.commit();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
    @Override
    @Transactional
    public int getWordCountByCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Word WHERE category = :category", Long.class);
        query.setParameter("category", category);
        Long count = query.getSingleResult();
        transaction.commit();
        return count.intValue();
    }
    @Override
    @Transactional
    public int getQuestionCountByCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Question WHERE category = :category", Long.class);
        query.setParameter("category", category);
        Long count = query.getSingleResult();
        transaction.commit();
        return count.intValue();
    }

    @Override
    public Category updateCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(category);
        transaction.commit();
        return category;
    }

    @Override
    @Transactional
    public Category getRandomCategory() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Category> query = session.createQuery("FROM Category ORDER BY RAND()", Category.class);
        query.setMaxResults(1);
        Category category = query.getSingleResult();
        Hibernate.initialize(category.getWords());
        Hibernate.initialize(category.getQuestions());
        transaction.commit();
        System.out.println(category.getQuestions().size());
        return category;
    }
}
