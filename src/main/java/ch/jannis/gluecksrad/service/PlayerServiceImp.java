package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.dao.PlayerDAO;
import ch.jannis.gluecksrad.model.Player;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Transactional
public class PlayerServiceImp implements PlayerService {
    @Autowired
    private PlayerDAO playerDAO;

    @Override
    public boolean savePlayer(Player player) {
        return playerDAO.savePlayer(player);
    }

    @Override
    public List<Player> getPlayers() {
        return playerDAO.getPlayers();
    }

    @Override
    public boolean deletePlayer(Player player) {
        return playerDAO.deletePlayer(player);
    }

    @Override
    public boolean updatePlayer(Player player) {
        return playerDAO.updatePlayer(player);
    }
}
