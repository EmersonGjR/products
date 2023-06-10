package com.rest.products.services.imple;

import com.rest.products.model.ProductModel;
import com.rest.products.repositories.ProductsRepository;
import com.rest.products.services.ProductsServices;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductsImp implements ProductsServices {
    private ProductsRepository productsRepository;
    ProductsImp (ProductsRepository productsRepository){
        this.productsRepository = productsRepository;
    }
    @Override
    public List<ProductModel> findAll() {return productsRepository.findAll();}
    @Override
    public ProductModel findById(UUID id){ return productsRepository.findById(id).get(); }
    @Override
    public ProductModel save(ProductModel productModel){ return productsRepository.save(productModel); }
    @Override
    public void delete(ProductModel productModel) { productsRepository.delete(productModel); }

}
