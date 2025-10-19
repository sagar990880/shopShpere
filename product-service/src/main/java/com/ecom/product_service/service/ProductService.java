package com.ecom.product_service.service;

import com.ecom.product_service.dto.ProductRequestDTO;
import com.ecom.product_service.dto.ProductResponseDTO;

import java.util.List;

public interface ProductService {

    ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    ProductResponseDTO getProductById(String productId);
    List<ProductResponseDTO>getAllProducts();
    ProductResponseDTO updateStock(String productId,Integer stockQuantity);
    String deleteProduct(String productId);
}
