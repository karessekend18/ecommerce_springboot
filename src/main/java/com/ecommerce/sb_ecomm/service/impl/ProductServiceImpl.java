package com.ecommerce.sb_ecomm.service.impl;

import com.ecommerce.sb_ecomm.dto.ProductRequest;
import com.ecommerce.sb_ecomm.dto.ProductResponse;
import com.ecommerce.sb_ecomm.exceptions.ResourceNotFoundException;
import com.ecommerce.sb_ecomm.model.Category;
import com.ecommerce.sb_ecomm.model.Product;
import com.ecommerce.sb_ecomm.repository.CategoryRepository;
import com.ecommerce.sb_ecomm.repository.ProductRepository;
import com.ecommerce.sb_ecomm.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.UUID;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ProductRequest addProduct(Long categoryId, ProductRequest productRequest)  {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        Product product = modelMapper.map(productRequest, Product.class);
        product.setImage("default.png");
        product.setCategory(category);
        double specialPrice = product.getPrice() - ((product.getDiscount() * 0.01) * product.getPrice());
        product.setSpecialPrice(specialPrice);
        Product savedProduct = productRepository.save(product);
        return modelMapper.map(savedProduct, ProductRequest.class);
    }

    @Override
    public ProductResponse getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductRequest> productRequests = products.stream()
                .map(product -> modelMapper.map(product, ProductRequest.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productRequests);
        return productResponse;
    }

    @Override
    public ProductResponse searchByCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "categoryId", categoryId));
        List<Product> products = productRepository.findByCategoryOrderByPriceAsc(category);
        List<ProductRequest> productRequests = products.stream()
                .map(product -> modelMapper.map(product, ProductRequest.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productRequests);
        return productResponse;
    }

    @Override
    public ProductResponse searchProductsByKeyword(String keyword) {
        List<Product> products = productRepository.findByProductNameLikeIgnoreCase('%' + keyword + '%');
        List<ProductRequest> productRequests = products.stream()
                .map(product -> modelMapper.map(product, ProductRequest.class))
                .toList();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(productRequests);
        return productResponse;
    }

    @Override
    public ProductRequest updateProduct(ProductRequest productRequest, Long productId) {
        //get existing product from DB
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        //update product info with the one in request body
        Product product = modelMapper.map(productRequest, Product.class);

        productFromDb.setProductName(product.getProductName());
        productFromDb.setDescription(product.getDescription());
        productFromDb.setPrice(product.getPrice());
        productFromDb.setDiscount(product.getDiscount());
        productFromDb.setQuantity(product.getQuantity());
        productFromDb.setSpecialPrice(product.getSpecialPrice());
        //save to DB
        Product savedProduct = productRepository.save(productFromDb);
        return modelMapper.map(savedProduct, ProductRequest.class);

    }

    @Override
    public ProductRequest deleteProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        productRepository.delete(product);
        return modelMapper.map(product, ProductRequest.class);

    }

    @Override
    public ProductRequest updateProductImage(Long productId, MultipartFile image) throws IOException {
        Product productFromDb = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product", "productId", productId));
        String path = "images/";
        String fileName = uploadImage(path, image);
        productFromDb.setImage(fileName);
        Product updatedProduct = productRepository.save(productFromDb);
        return modelMapper.map(updatedProduct, ProductRequest.class);

    }

    private String uploadImage(String path, MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        String randomId = UUID.randomUUID().toString();
        String fileName = randomId.concat(originalFilename.substring(originalFilename.lastIndexOf(".")));
        String filePath = path + File.separator + fileName;
        File folder = new File(path);
        if (!folder.exists()) {
            folder.mkdirs();
        }
        Files.copy(file.getInputStream(), new File(filePath).toPath());
        return fileName;
    }
}
