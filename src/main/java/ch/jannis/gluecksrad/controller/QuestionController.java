package ch.jannis.gluecksrad.controller;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.model.Question;
import ch.jannis.gluecksrad.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowCredentials = "true")
@RequestMapping(value = "/api")
public class QuestionController {
    @Autowired
    QuestionService questionService;

    @PostMapping("save-question")
    public boolean saveCategory(@RequestBody Question question) {
        Category category = question.getCategory();
        return questionService.saveQuestion(question, category);
    }

    @GetMapping("question-list")
    public List<Question> getCategoryList() {
        return questionService.getQuestionList();
    }
    @PostMapping("update-question")
    public Question updateQuestion(@RequestBody Question question) {
        return questionService.updateQuestion(question);
    }

    @PostMapping("delete-question")
    public boolean deleteQuestion(@RequestBody Question question) {
        return questionService.deleteQuestion(question);
    }
}
