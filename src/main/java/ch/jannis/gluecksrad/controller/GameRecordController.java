package ch.jannis.gluecksrad.controller;

import ch.jannis.gluecksrad.model.Game;
import ch.jannis.gluecksrad.model.GameRecord;
import ch.jannis.gluecksrad.service.GameRecordService;
import ch.jannis.gluecksrad.service.GameService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value = "/api")
public class GameRecordController {
    @Autowired
    GameService gameService;
    @Autowired
    GameRecordService gameRecordService;

    @PostMapping("save-game")
    public boolean saveGame(HttpServletRequest request, @RequestBody String playername) {
        HttpSession session = request.getSession();
        Game game = gameService.getGame(session);
        gameService.resetGame(session);
        return gameRecordService.saveGame(game, playername);
    }

    @GetMapping("gamerecord-list")
    public List<GameRecord> allWords() {
        List<GameRecord> allRecords = gameRecordService.getRecords();
        return allRecords;
    }

    @PostMapping("delete-record")
    public boolean saveGame(HttpServletRequest request, @RequestBody GameRecord record) {
        HttpSession session = request.getSession();
        return gameRecordService.deleteRecord(record);
    }
}
