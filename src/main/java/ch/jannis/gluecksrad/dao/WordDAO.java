package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Word;

import java.util.List;

public interface WordDAO {
    public boolean saveWord(Word word);
    public List<Word> getWordsList();
    public Word updateWord(Word word);
    public String[] getThreeWordsOfCategory(Category category);

    boolean deleteWord(Word word);
}
