package com.ary.springboot.di.app.springbootdi.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ary.springboot.di.app.springbootdi.models.Product;
import com.ary.springboot.di.app.springbootdi.services.ProductService;



@RestController
@RequestMapping("/api")
public class SomeController {
    private ProductService service=new ProductService();

    @GetMapping
    public List<Product>list(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product show(@PathVariable Long  id) {
        return service.findById(id);
    }
    

}
