package com.ary.springboot.di.app.springbootdi.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.ary.springboot.di.app.springbootdi.models.Product;
import com.ary.springboot.di.app.springbootdi.repositories.ProductRepository;
@Service
public class ProductServiceImpl implements ProductService{
    //en caso de hacer la inyeccion con autowired como atributo va en @qualifier abajo de @Auto
    private ProductRepository repository;
    //se esta inyectando el repositorio mediante el contructor que no va con autowired
    //tambien se puede poner en qualifier un name desde el componente
    public ProductServiceImpl(@Qualifier("productRepositoryImpl") ProductRepository repository) {
        this.repository = repository;
    }
    @Override
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
    @Override
    public Product findById(Long id ){
        return repository.findById(id);
    }

}
