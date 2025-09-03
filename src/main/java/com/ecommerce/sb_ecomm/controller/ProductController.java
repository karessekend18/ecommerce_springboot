package com.ecommerce.sb_ecomm.controller;

import com.ecommerce.sb_ecomm.dto.ProductRequest;
import com.ecommerce.sb_ecomm.model.Product;
import com.ecommerce.sb_ecomm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/admin/categories/{categoryId}/product")
    public ResponseEntity<ProductRequest> addProduct(@RequestBody Product product,
                                                     @PathVariable Long categoryId) {
        ProductRequest productRequest = productService.addProduct(categoryId, product);
        return new ResponseEntity<>(productRequest, HttpStatus.CREATED);
    }

}
