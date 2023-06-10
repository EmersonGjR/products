package com.rest.products.controllers;

import com.rest.products.dtos.ProductsDto;
import com.rest.products.model.ProductModel;
import com.rest.products.services.ProductsServices;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
public class ProductsController {

    private ProductsServices productsServices;

    public ProductsController ( ProductsServices productsServices ){

        this.productsServices = productsServices;

    }
    @PostMapping("/products")
    public ResponseEntity<Object> saveProduct(@RequestBody @Valid ProductsDto productsDto){
        var productModel = new ProductModel();
        BeanUtils.copyProperties(productsDto, productModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(productsServices.save(productModel));
    }
    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> getall(){
        return ResponseEntity.status(HttpStatus.OK).body(productsServices.findAll());
    }
    @GetMapping("/products/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value="id") UUID id){
        Optional<ProductModel> product = Optional.ofNullable(productsServices.findById(id));
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        return ResponseEntity.status(HttpStatus.OK).body(product.get());
    }
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable(value="id") UUID id, @RequestBody @Valid ProductsDto productsDto){
        Optional<ProductModel> product = Optional.ofNullable(productsServices.findById(id));
        if(product.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        var productModel = product.get();
        BeanUtils.copyProperties(productsDto, productModel);
        return ResponseEntity.status(HttpStatus.OK).body(productsServices.save(productModel));
    }
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Object> deleteOne(@PathVariable(value = "id") UUID id) {
        Optional<ProductModel> productOptional = Optional.ofNullable(productsServices.findById(id));
        if (productOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        ProductModel product = productOptional.get();
        productsServices.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted");
    }


}
