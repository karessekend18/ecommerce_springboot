package com.ecommerce.sb_ecomm.controller;

import com.ecommerce.sb_ecomm.dto.ProductRequest;
import com.ecommerce.sb_ecomm.dto.ProductResponse;
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
    public ResponseEntity<ProductRequest> addProduct(@RequestBody ProductRequest productRequest,
                                                     @PathVariable Long categoryId) {
        ProductRequest savedProductRequest = productService.addProduct(categoryId, productRequest);
        return new ResponseEntity<>(savedProductRequest, HttpStatus.CREATED);
    }

    @GetMapping("/public/products")
    public ResponseEntity<ProductResponse> getAllProducts() {
        ProductResponse productResponse = productService.getAllProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/categories/{categoryId}/products")
    public ResponseEntity<ProductResponse> getProductsByCategory(@PathVariable Long categoryId) {
        ProductResponse productResponse = productService.searchByCategory(categoryId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    @GetMapping("/public/products/keyword/{keyword}")
    public ResponseEntity<ProductResponse> getProductsByKeyword(@PathVariable String keyword) {
        ProductResponse productResponse = productService.searchProductsByKeyword(keyword);
        return new ResponseEntity<>(productResponse, HttpStatus.FOUND);
    }

    @GetMapping("/admin/products/{productId}")
    public ResponseEntity<ProductRequest> updateProduct(@RequestBody ProductRequest productRequest,
                                                        @PathVariable Long productId) {
        ProductRequest updatedProductRequest = productService.updateProduct(productRequest, productId);
        return new ResponseEntity<>(updatedProductRequest, HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{productId}")
    public ResponseEntity<ProductRequest> deleteProduct(@PathVariable Long productId) {
        ProductRequest productRequest = productService.deleteProduct(productId);
        return new ResponseEntity<>(productRequest, HttpStatus.OK);
    }


}
