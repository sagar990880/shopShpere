package com.ecom.product_service.mapper;

import com.ecom.product_service.dto.ProductResponseDTO;
import com.ecom.product_service.entity.Product;

public class ProductMapping {


    public static ProductResponseDTO toProductResponseDTO(Product product)
    {
        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setProductId(product.getProductId());
        productResponseDTO.setName(product.getName());
        productResponseDTO.setDescription(product.getDescriptions());
        productResponseDTO.setPrice(product.getPrice());
        productResponseDTO.setStockQuantity(product.getStockQuantity());
        productResponseDTO.setInStock(product.getInStock());
        productResponseDTO.setCategoryName(product.getCategory().getName());
        return productResponseDTO;
    }
    public static Product toProductEntity(ProductResponseDTO productResponseDTO)
    {
        Product product=new Product();
        product.setProductId(productResponseDTO.getProductId());
        product.setName(productResponseDTO.getName());
        product.setDescriptions(productResponseDTO.getDescription());
        product.setPrice(productResponseDTO.getPrice());
        product.setStockQuantity(productResponseDTO.getStockQuantity());
        product.setInStock(productResponseDTO.getInStock());
        return product;
    }
}
