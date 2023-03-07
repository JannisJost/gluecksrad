package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Question;

import java.util.List;

public interface QuestionDAO {
    boolean saveQuestion(Question question, Category category);

    List<Question> getQuestionList();

    List<Question> getShuffledQuestionsOfCategory(Category category);

    Question getQuestion();

    Question updateQuestion(Question question);

    boolean deleteQuestion(Question question);
}
