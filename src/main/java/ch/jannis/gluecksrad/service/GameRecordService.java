package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.model.Game;
import ch.jannis.gluecksrad.model.GameRecord;

import java.util.List;

public interface GameRecordService {
    boolean saveGame(Game game, String playername);

    List<GameRecord> getRecords();
    boolean deleteRecord(GameRecord record);
}
