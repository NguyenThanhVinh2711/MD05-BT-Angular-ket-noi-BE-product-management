package com.example.demo.controller;

import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.service.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
@RequestMapping("/categories")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("")
    public ResponseEntity<Iterable<Category>> findAllProduct() {
        Iterable<Category> categories = categoryService.findAll();
        return new ResponseEntity<>(categories, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Category> createProduct(@RequestBody Category category){
        categoryService.save(category);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Category> updateCourse(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> category1 = categoryService.findById(id);
        if (!category1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        category.setId(category1.get().getId());
        return new ResponseEntity<>(categoryService.save(category), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> findByID(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(category.get() , HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteCourse(@PathVariable Long id) {
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        categoryService.remove(id);
        return new ResponseEntity<>(category.get(), HttpStatus.OK);
    }
}