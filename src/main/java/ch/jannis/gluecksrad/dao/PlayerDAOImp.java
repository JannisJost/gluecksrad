package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Player;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PlayerDAOImp implements PlayerDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public boolean savePlayer(Player player) {
        boolean status = false;
        try {
            Session session = sessionFactory.getCurrentSession();
            Transaction transaction = session.beginTransaction();
            session.persist(player);
            transaction.commit();
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    @Override
    public List<Player> getPlayers() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<Player> query = currentSession.createQuery("from player", Player.class);
        return query.getResultList();
    }

    @Override
    public boolean deletePlayer(Player player) {
        boolean status = false;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hql = "DELETE FROM Entity WHERE id = :id";
            int deletedEntities = session.createQuery(hql)
                    .setParameter("id", player.getPlayer_id())
                    .executeUpdate();
            session.getTransaction().commit();
        }
        return status;
    }

    @Override
    public boolean updatePlayer(Player player) {
        boolean status = false;
        try {
            sessionFactory.getCurrentSession().merge(player);
            status = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }
}
