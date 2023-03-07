package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Question;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class QuestionDAOImp implements QuestionDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean saveQuestion(Question question, Category category) {
        boolean status = false;
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            Category loadedCategory = session.load(Category.class, category.getId());
            question.setCategory(loadedCategory);
            Question merged = session.merge(question);
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
    public List<Question> getQuestionList() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Question> query = session.createQuery("FROM Question", Question.class);
        List<Question> list = query.getResultList();
        for (Question question : list) {
            Hibernate.initialize(question.getCategory());
            Hibernate.initialize(question.getCategory().getWords());
            Hibernate.initialize(question.getCategory().getQuestions());
        }
        transaction.commit();
        return list;
    }

    public List<Question> getShuffledQuestionsOfCategory(Category category){
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Question> query = session.createQuery("FROM Question w WHERE w.category = :category", Question.class);
        query.setParameter("category", category);
        List<Question> questions = query.getResultList();
        Collections.shuffle(questions);
        transaction.commit();
        return questions;
    }

    @Override
    public Question getQuestion() {
        return null;
    }

    @Override
    public Question updateQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(question);
        transaction.commit();
        return question;
    }

    @Override
    public boolean deleteQuestion(Question question) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Question attachedQuestion = session.merge(question);
        session.remove(attachedQuestion);
        transaction.commit();
        return true;
    }
}
