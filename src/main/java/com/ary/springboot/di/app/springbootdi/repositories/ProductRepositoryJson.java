package com.ary.springboot.di.app.springbootdi.repositories;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;


//import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.ary.springboot.di.app.springbootdi.models.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements ProductRepository {


    private List<Product> list;
    

    public ProductRepositoryJson(Resource resurce) {
    // Resource resurce = new ClassPathResource("json/product.json");

        ObjectMapper mapper= new ObjectMapper();
        try {
            list=Arrays.asList(mapper.readValue(resurce.getInputStream(),Product[].class));
        } catch (IOException e) {
            
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> findAll() {
       return list;
    }

    @Override
    public Product findById(Long id) {
        return list.stream().filter(p-> p.getId().equals(id)).findFirst().orElseThrow();
    }

}
