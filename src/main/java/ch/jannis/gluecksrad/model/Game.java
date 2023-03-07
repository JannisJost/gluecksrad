package ch.jannis.gluecksrad.model;


import java.util.List;
import java.util.Objects;
import java.util.Random;

public class Game {
    private String[] words;
    private String[] guessedChars = {""};
    private int questionAmount = -1;
    private int[] options = {200, 500, 700, 1000, -1};
    private int nextAction = 0;
    private boolean spin = true;
    private int balance = 0;
    private int lives = 3;
    private boolean needToAnswerQuestion = false;
    private int rounds = 0;
    private Category category;
    private List<Question> questions;
    private Question question = new Question();
    private boolean gameover = false;
    private boolean vowelShopping = false;
    private final String[] vowels = {"A", "E", "I", "O", "U"};

    public Game(String[] words, Category category, List<Question> questions) {
        this.questions = questions;
        this.category = category;
        this.words = words;
    }

    public boolean isNeedToAnswerQuestion() {
        return needToAnswerQuestion;
    }

    public Category getCategory() {
        return category;
    }

    public String[] getWords() {
        return words;
    }

    public boolean isVowelShopping() {
        return vowelShopping;
    }

    public boolean isGameover() {
        return gameover;
    }

    public String[] getGuessedChars() {
        return guessedChars;
    }

    public boolean isSpin() {
        return spin;
    }

    public int getBalance() {
        return balance;
    }

    public Question getQuestion() {
        return question;
    }

    public boolean guessChar(String guess) {
        boolean valid = false;
        rounds++;
        guess = guess.replace("\"", "");
        if (guessIsRight(guess)) {
            valid = true;
            if (isVowel(guess)) {
                balance -= 200;
                vowelShopping = false;
                spin = true;
            } else {
                balance += nextAction * timesContained(guess);
                if (balance >= 200) {
                    nextAction = 0;
                    vowelShopping = true;
                }
            }
        } else {
            if (vowelShopping) {
                vowelShopping = false;
            }
            --lives;
            spin = true;
        }
        String[] newArray = new String[guessedChars.length + 1];
        System.arraycopy(guessedChars, 0, newArray, 0, guessedChars.length);
        newArray[newArray.length - 1] = guess;
        guessedChars = newArray;
        gameover = checkIfGameover();
        return valid;
    }

    private boolean isVowel(String value) {
        for (String vowel : vowels) {
            if (vowel.equals(value)) return true;
        }
        return false;
    }

    public boolean skipVowelShopping() {
        vowelShopping = false;
        spin = true;
        return true;
    }

    public boolean setQuestionAmount(int amount) {
        this.questionAmount = amount;
        return true;
    }

    public int getLives() {
        return lives;
    }

    public int getRounds() {
        return rounds;
    }

    public int getQuestionAmount() {
        return questionAmount;
    }

    public boolean answerQuestion(String answer) {
        answer = answer.substring(1, answer.length() - 1);
        boolean result = Objects.equals(question.getRightAnswer(), answer);
        if (result) {
            balance += questionAmount;
        } else {
            balance -= questionAmount;
        }
        spin = true;
        needToAnswerQuestion = false;
        questionAmount = -1;
        return result;
    }

    private int timesContained(String guess) {
        int count = 0;
        for (String word : words) {
            int index = word.indexOf(guess);
            while (index != -1) {
                count++;
                index = word.indexOf(guess, index + 1);
            }
        }

        return count;
    }

    private boolean guessIsRight(String guess) {
        for (String word : words) {
            if (word.contains(guess)) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIfGameover() {
        int numberOfWords = words.length;
        if (guessedChars.length == 27) {
            return true;
        } else if (lives <= 0) {
            return true;
        } else if (balance < 0) {
            return true;
        }
        for (String word : words) {
            String tempWord = word;
            for (String character : guessedChars) {
                tempWord = tempWord.replace(character, "");
                if (tempWord.length() == 0) {
                    numberOfWords -= 1;
                    break;
                }
            }
        }
        return numberOfWords <= 0;
    }

    public void spin() {
        Random random = new Random();
        int chance = random.nextInt(options.length);
        nextAction = options[chance];
        //-2 means instant game over
        if (nextAction == -2) {
            gameover = true;
            //-1 means question time
        } else if (nextAction == -1) {
            if (questions.size() > 0) {
                needToAnswerQuestion = true;
                question = questions.get(0);
                questions.remove(0);
            } else {
                spin();
            }
        }
        spin = false;
    }

    public int getNextAction() {
        return nextAction;
    }
}
