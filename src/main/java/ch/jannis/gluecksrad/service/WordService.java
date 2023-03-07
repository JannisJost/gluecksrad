package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.model.Word;

import java.util.List;

public interface WordService {
    public List<Word> getWords();
    public boolean saveWord(Word word);
    public Word updateWord(Word word);
    public boolean deleteWord(Word word);
}
