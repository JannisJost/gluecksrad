package ch.jannis.gluecksrad.controller;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Question;

public class QuestionData{

    private Question question;
    private Category category;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
