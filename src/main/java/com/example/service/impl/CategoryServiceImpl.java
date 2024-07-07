package com.example.service.impl;

import com.example.model.Category;
import com.example.repository.CategoryRepository;
import com.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category save(Category category, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            throw new IllegalArgumentException();
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category update(Category category, Long id) {
        category.setId(id);
        return categoryRepository.save(category);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public Category delete(Long id) {
        Category category = findById(id);
        categoryRepository.delete(category);
        return category;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> findByName(String name) {
        return categoryRepository.findByName(name);
    }
}
