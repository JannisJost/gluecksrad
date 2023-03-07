package ch.jannis.gluecksrad.controller;

import ch.jannis.gluecksrad.model.Game;
import ch.jannis.gluecksrad.service.GameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value = "/api")
public class GameController {
    @Autowired
    GameService gameService;


    @GetMapping("current-game")
    public Game getGame(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return gameService.getGame(session);
    }

    @GetMapping("spin")
    public boolean spin(HttpServletRequest request) {
        HttpSession session = request.getSession();
        gameService.getGame(session).spin();
        return true;
    }

    @PostMapping("guess-char")
    public boolean guessCharacter(@RequestBody String guess, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return gameService.getGame(session).guessChar(guess);
    }

    @PostMapping("answer-question")
    public boolean answerQuestion(@RequestBody String answer, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return gameService.getGame(session).answerQuestion(answer);
    }

    @PostMapping("set-amount")
    public boolean setQuestionAmount(@RequestBody int amount, HttpServletRequest request) {
        HttpSession session = request.getSession();
        return gameService.getGame(session).setQuestionAmount(amount);
    }

    @GetMapping("skip-vowel-shopping")
    public boolean skipVowelShopping(HttpServletRequest request) {
        HttpSession session = request.getSession();
        gameService.getGame(session).skipVowelShopping();
        return true;
    }

    @PostMapping("reset-game")
    public boolean newGame(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return gameService.resetGame(session);
    }
}
