package com.ecommerce.sb_ecomm.service;

import com.ecommerce.sb_ecomm.dto.ProductRequest;
import com.ecommerce.sb_ecomm.dto.ProductResponse;
import com.ecommerce.sb_ecomm.model.Product;

public interface ProductService {

    ProductRequest addProduct(Long categoryId, Product product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductsByKeyword(String keyword);

    ProductRequest updateProduct(Product product, Long productId);

    ProductRequest deleteProduct(Long productId);
}
