package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.model.Game;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

@Service
public interface GameService {
    Game getGame(HttpSession session);

    boolean resetGame(HttpSession session);
}
