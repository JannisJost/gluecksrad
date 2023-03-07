package ch.jannis.gluecksrad.service;

import ch.jannis.gluecksrad.dao.CategoryDAO;
import ch.jannis.gluecksrad.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryDAO categoryDAO;

    @Override
    public List<Category> getCategoriesList() {
        return categoryDAO.getCategoriesList();
    }

    @Override
    public boolean saveCategory(Category category) {
        if (category.getName().length() > 2) {
            return categoryDAO.saveCategory(category);
        }
        return false;
    }

    @Override
    public Category getRandomCategory() {
        return categoryDAO.getRandomCategory();
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDAO.updateCategory(category);
    }
}
