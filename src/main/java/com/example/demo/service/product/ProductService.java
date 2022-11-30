package com.example.demo.service.product;

import com.example.demo.model.Product;
import com.example.demo.repository.IProductRepo;
import com.example.demo.service.product.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductService implements IProductService {

    @Autowired
    IProductRepo productRepo;

    @Override
    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepo.findById(id);
    }

    @Override
    public Product save(Product product) {
        return productRepo.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepo.deleteById(id);
    }
}
