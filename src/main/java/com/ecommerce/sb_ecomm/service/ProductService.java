package com.ecommerce.sb_ecomm.service;

import com.ecommerce.sb_ecomm.dto.ProductRequest;
import com.ecommerce.sb_ecomm.dto.ProductResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ProductService {

    ProductRequest addProduct(Long categoryId, ProductRequest product);

    ProductResponse getAllProducts();

    ProductResponse searchByCategory(Long categoryId);

    ProductResponse searchProductsByKeyword(String keyword);

    ProductRequest updateProduct(ProductRequest product, Long productId);

    ProductRequest deleteProduct(Long productId);

    ProductRequest updateProductImage(Long productId, MultipartFile image) throws IOException;
}
