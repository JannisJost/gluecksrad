package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Player;

import java.util.List;

public interface PlayerDAO {
    public boolean savePlayer(Player player);

    public List<Player> getPlayers();

    public boolean deletePlayer(Player player);

    public boolean updatePlayer(Player player);
}
