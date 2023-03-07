package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.dao.GameRecordDAO;
import ch.jannis.gluecksrad.model.Game;
import ch.jannis.gluecksrad.model.GameRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class GameRecordServiceImp implements GameRecordService {
    @Autowired
    GameRecordDAO gameRecordDAO;

    @Override
    public boolean saveGame(Game game, String playername) {
        GameRecord gameRecord = new GameRecord();
        gameRecord.setPlayername(playername.replace("\"", ""));
        gameRecord.setBalance(game.getBalance());
        gameRecord.setNumberOfRounds(String.valueOf(game.getRounds()));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        LocalDateTime currentDateTime = LocalDateTime.now();
        String currentTime = currentDateTime.format(formatter);
        gameRecord.setDateAndTime(currentTime);
        return gameRecordDAO.saveGameRecord(gameRecord);
    }

    @Override
    public List<GameRecord> getRecords() {
        return gameRecordDAO.getRecords();
    }

    @Override
    public boolean deleteRecord(GameRecord record) {
        return gameRecordDAO.deleteRecord(record);
    }
}
