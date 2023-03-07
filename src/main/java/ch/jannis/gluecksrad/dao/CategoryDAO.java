package ch.jannis.gluecksrad.dao;

import ch.jannis.gluecksrad.model.Category;
import jakarta.transaction.Transactional;

import java.util.List;

public interface CategoryDAO {
    public List<Category> getCategoriesList();
    public boolean saveCategory(Category category);
    public Category getRandomCategory();

    @Transactional
    int getWordCountByCategory(Category category);

    public int getQuestionCountByCategory(Category category);

    public Category updateCategory(Category category);
}
