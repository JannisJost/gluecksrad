package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.GameRecord;
import ch.jannis.gluecksrad.model.Question;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GameRecordDAOImp implements GameRecordDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public boolean saveGameRecord(GameRecord gameRecord) {
        boolean status = false;
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.persist(gameRecord);
            transaction.commit();
            status = true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<GameRecord> getRecords() {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Query<GameRecord> query = session.createQuery("FROM GameRecord ORDER BY balance DESC", GameRecord.class);
        List<GameRecord> list = query.getResultList();
        transaction.commit();
        return list;
    }

    @Override
    public boolean deleteRecord(GameRecord record) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        GameRecord attachedRecord = session.merge(record);
        session.remove(attachedRecord);
        transaction.commit();
        return true;
    }
}
