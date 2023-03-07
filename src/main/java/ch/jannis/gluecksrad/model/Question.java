package ch.jannis.gluecksrad.model;

import jakarta.persistence.*;

@Entity
public class Question {
    public void setCategory(Category category) {
        this.category = category;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String question = "";
    private String rightAnswer = "";
    private String wrongAnswer = "";
    @ManyToOne(fetch = FetchType.EAGER)
    Category category;

    public String getQuestion() {
        return question;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public String getWrongAnswer() {
        return wrongAnswer;
    }

    public Category getCategory() {
        return category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
