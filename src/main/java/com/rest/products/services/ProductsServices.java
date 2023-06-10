package com.rest.products.services;

import com.rest.products.model.ProductModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


public interface ProductsServices {
    List<ProductModel> findAll();
    ProductModel findById(UUID id);
    ProductModel save(ProductModel productModel);
    void delete(ProductModel productModel);

}
