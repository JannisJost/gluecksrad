package ch.jannis.gluecksrad.controller;

import ch.jannis.gluecksrad.model.Category;
import ch.jannis.gluecksrad.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "/api")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("category-list")
    public List<Category> getCategoryList() {
        System.out.println("Getting category list");
        return categoryService.getCategoriesList();
    }

    @PostMapping("add-category")
    public boolean saveCategory(@RequestBody Category category) {
        return categoryService.saveCategory(category);
    }

    @PostMapping("update-category")
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }
}
