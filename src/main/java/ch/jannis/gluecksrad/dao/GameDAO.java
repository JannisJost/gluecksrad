package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Game;
import jakarta.servlet.http.HttpSession;

public interface GameDAO {
    public Game getGame(HttpSession session);
    boolean resetGame(HttpSession session);
}
