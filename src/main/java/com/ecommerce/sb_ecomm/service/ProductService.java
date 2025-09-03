package com.ecommerce.sb_ecomm.service;

import com.ecommerce.sb_ecomm.dto.ProductRequest;
import com.ecommerce.sb_ecomm.model.Product;

public interface ProductService {

    ProductRequest addProduct(Long categoryId, Product product);
}
