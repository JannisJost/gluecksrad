package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Question;

import java.util.List;

public interface QuestionService {
    boolean saveQuestion(Question question, Category category);

    Question getQuestion();

    List<Question> getQuestionList();

    Question updateQuestion(Question question);

    boolean deleteQuestion(Question question);
}
