package com.ecommerce.sb_ecomm.service;

import com.ecommerce.sb_ecomm.dto.ProductRequest;
import com.ecommerce.sb_ecomm.dto.ProductResponse;

public interface ProductService {

    ProductRequest addProduct(Long categoryId, ProductRequest product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductsByKeyword(String keyword);

    ProductRequest updateProduct(ProductRequest product, Long productId);

    ProductRequest deleteProduct(Long productId);
}
