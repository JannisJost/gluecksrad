package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.dao.GameDAO;
import ch.jannis.gluecksrad.model.Game;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImp implements GameService {
    @Autowired
    GameDAO gameDAO;

    @Override
    public Game getGame(HttpSession session) {
        return gameDAO.getGame(session);
    }

    @Override
    public boolean resetGame(HttpSession session) {
        return gameDAO.resetGame(session);
    }
}
