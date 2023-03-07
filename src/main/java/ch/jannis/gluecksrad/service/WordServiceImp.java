package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.dao.WordDAO;
import ch.jannis.gluecksrad.model.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImp implements WordService {
    @Autowired
    private WordDAO wordDAO;

    @Override
    public List<Word> getWords() {
        return wordDAO.getWordsList();
    }

    @Override
    public boolean saveWord(Word word) {
        if (word.getWord().length() > 2) {
            return wordDAO.saveWord(word);
        }
        return false;
    }

    @Override
    public Word updateWord(Word word) {
        return wordDAO.updateWord(word);
    }

    @Override
    public boolean deleteWord(Word word) {
        return wordDAO.deleteWord(word);
    }
}
