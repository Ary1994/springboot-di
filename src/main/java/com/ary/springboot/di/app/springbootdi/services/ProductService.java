package com.ary.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import com.ary.springboot.di.app.springbootdi.models.Product;
import com.ary.springboot.di.app.springbootdi.repositories.ProductRepository;

public class ProductService {

    private ProductRepository repository=new ProductRepository();
    public List<Product> findAll(){
        return repository.findAll().stream().map(p -> {
            Double priceImp=p.getPrice()*1.25d;
            //p.setPrice(priceImp.longValue()); hacerlo asi rompe el princio de inmutabilidad
//crea un nuevo producto y le devulve 
            //Product newProduct=new Product(p.getId(),p.getName(),priceImp.longValue());
            //este esta forma es con un metodo de la implementacion de clonable
            Product newProduct=(Product)p.clone();
            newProduct.setPrice(priceImp.longValue());
            return newProduct;
        }).collect(Collectors.toList());
    }

    public Product findById(Long id ){
        return repository.findById(id);
    }

}
