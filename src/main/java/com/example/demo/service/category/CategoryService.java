package com.example.demo.service.category;

import com.example.demo.model.Category;
import com.example.demo.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {

    @Autowired
    ICategoryRepo categoryRepo;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepo.findAll();
    }

    @Override
    public Optional<Category> findById(Long id) {
        return categoryRepo.findById(id);
    }

    @Override
    public Category save(Category category) {
        return categoryRepo.save(category);
    }

    @Override
    public void remove(Long id) {
        categoryRepo.deleteById(id);
    }
}
