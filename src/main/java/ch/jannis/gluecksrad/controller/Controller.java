package ch.jannis.gluecksrad.controller;

import ch.jannis.gluecksrad.model.Player;
import ch.jannis.gluecksrad.model.Word;
import ch.jannis.gluecksrad.service.PlayerService;
import ch.jannis.gluecksrad.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value = "/api")
public class Controller {
    @Autowired
    private PlayerService playerService;
    @Autowired
    private WordService wordService;

    @PostMapping("save-player")
    public boolean savePlayer(@RequestBody Player player) {
        return playerService.savePlayer(player);
    }

    @GetMapping("player-list")
    public List<Player> allPlayers() {
        return playerService.getPlayers();
    }

    @DeleteMapping("delete-player/{player_id}")
    public boolean deletePlayer(@PathVariable("player_id") int player_id, Player player) {
        player.setPlayer_id(player_id);
        return playerService.deletePlayer(player);
    }

    @PostMapping("update-player/{player_id}")
    public boolean updateStudent(@RequestBody Player player, @PathVariable("player_id") int player_id) {
        player.setPlayer_id(player_id);
        return playerService.updatePlayer(player);
    }

    @GetMapping("word-list")
    public List<Word> allWords() {
        List<Word> allWords = wordService.getWords();
        return allWords;
    }

    @PostMapping("save-word")
    public boolean saveWord(@RequestBody Word word) {
        return wordService.saveWord(word);
    }

    @PostMapping("update-word")
    public Word updateWord(@RequestBody Word word) {
        return wordService.updateWord(word);
    }

    @PostMapping("delete-word")
    public boolean deleteWord(@RequestBody Word word) {
        return wordService.deleteWord(word);
    }
}
