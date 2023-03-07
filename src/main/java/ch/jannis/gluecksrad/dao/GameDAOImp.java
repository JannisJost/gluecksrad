package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Game;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GameDAOImp implements GameDAO {

    @Autowired
    WordDAO wordDAO;
    @Autowired
    CategoryDAO categoryDAO;

    @Autowired
    QuestionDAO questionDAO;

    @Override
    public Game getGame(HttpSession session) {
        if (session.getAttribute("game") == null) {
            Category category;
            do {
                category = categoryDAO.getRandomCategory();
            } while (categoryDAO.getWordCountByCategory(category) < 3 && categoryDAO.getQuestionCountByCategory(category) < 3);
            session.setAttribute("game", new Game(wordDAO.getThreeWordsOfCategory(category), category, questionDAO.getShuffledQuestionsOfCategory(category)));
        }
        return (Game) session.getAttribute("game");
    }

    @Override
    public boolean resetGame(HttpSession session) {
        System.out.println(session);
        session.invalidate();
        return true;
    }
}
