package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.model.Player;

import java.util.List;

public interface PlayerService {
    public boolean savePlayer(Player player);

    public List<Player> getPlayers();

    public boolean deletePlayer(Player player);

    public boolean updatePlayer(Player player);
}
