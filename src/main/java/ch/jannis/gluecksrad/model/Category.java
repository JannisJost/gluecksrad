package ch.jannis.gluecksrad.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @OneToMany(mappedBy = "word", fetch =  FetchType.EAGER)
    private List<Word> words;

    @OneToMany(mappedBy = "question" , fetch = FetchType.EAGER)
    private List<Question> questions;

    public String getName() {
        return name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Column(name = "category_name")
    private String name ="";

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Word> getWords() {
        return words;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
