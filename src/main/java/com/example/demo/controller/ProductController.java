package com.example.demo.controller;


import com.example.demo.model.Product;
import com.example.demo.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/product")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Iterable<Product>> findAllProduct() {
        Iterable<Product> products = productService.findAll();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
       productService.save(product);
       return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Product> updateCourse(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> product1 = productService.findById(id);
        if (!product1.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        product.setId(product1.get().getId());
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Product> findByID(@PathVariable Long id){
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(product.get() , HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteCourse(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (!product.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productService.remove(id);
        return new ResponseEntity<>(product.get(), HttpStatus.OK);
    }
}
