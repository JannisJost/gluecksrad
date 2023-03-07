package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.GameRecord;

import java.util.List;

public interface GameRecordDAO {
    boolean saveGameRecord(GameRecord gameRecord);

    List<GameRecord> getRecords();

    boolean deleteRecord(GameRecord record);
}
