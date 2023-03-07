package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Word;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Random;

@Repository
public class WordDAOImp implements WordDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean saveWord(Word word) {
        boolean status = false;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.getTransaction();
        try {
            if (transaction.isActive()) {
                transaction.commit();
            }
            transaction.begin();
            Word attachedWord = session.merge(word);
            session.persist(attachedWord);
            transaction.commit();
            status = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return status;
    }

    @Override
    @Transactional
    public List<Word> getWordsList() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Word> query = session.createQuery("FROM Word", Word.class);
        List<Word> list = query.getResultList();
        for (Word word : list) {
            Hibernate.initialize(word.getCategory());
            Hibernate.initialize(word.getCategory().getWords());
            Hibernate.initialize(word.getCategory().getQuestions());
        }
        transaction.commit();
        return list;
    }

    private List<Word> getWordsOfCategory(Category category) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<Word> query = session.createQuery("FROM Word w WHERE w.category = :category", Word.class);
        query.setParameter("category", category);
        List<Word> words = query.getResultList();
        transaction.commit();
        return words;
    }

    @Override
    public String[] getThreeWordsOfCategory(Category category) {
        List<Word> allWords = getWordsOfCategory(category);
        String[] words = new String[3];
        for (int i = 0; i < 3; i++) {
            Random random = new Random();
            int randomInt = random.nextInt(allWords.size());
            words[i] = allWords.get(randomInt).getWord().toUpperCase();
            allWords.remove(randomInt);
        }
        return words;
    }

    @Override
    public Word updateWord(Word word) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.merge(word);
        transaction.commit();
        return word;
    }

    @Override
    public boolean deleteWord(Word word) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Word attachedWord = session.merge(word);
        session.remove(attachedWord);
        transaction.commit();
        return true;
    }
}
