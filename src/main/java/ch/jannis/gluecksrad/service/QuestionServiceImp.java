package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.dao.QuestionDAO;
import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImp implements QuestionService {
    @Autowired
    QuestionDAO questionDAO;

    @Override
    public boolean saveQuestion(Question question, Category category) {
        if (question.getQuestion().length() > 5 && question.getRightAnswer().length() > 2 && question.getWrongAnswer().length() > 2) {
            return questionDAO.saveQuestion(question, category);
        }
        return false;
    }

    @Override
    public Question getQuestion() {
        return questionDAO.getQuestion();
    }

    @Override
    public List<Question> getQuestionList() {
        return questionDAO.getQuestionList();
    }

    @Override
    public Question updateQuestion(Question question) {
        return questionDAO.updateQuestion(question);
    }

    @Override
    public boolean deleteQuestion(Question question) {
        return questionDAO.deleteQuestion(question);
    }
}
