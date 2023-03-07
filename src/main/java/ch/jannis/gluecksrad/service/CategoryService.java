package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.model.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategoriesList();
    public boolean saveCategory(Category category);
    public Category getRandomCategory();
    public Category updateCategory(Category category);
}
