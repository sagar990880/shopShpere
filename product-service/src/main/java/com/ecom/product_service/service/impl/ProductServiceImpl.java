package com.ecom.product_service.service.impl;

import com.ecom.product_service.dto.ProductRequestDTO;
import com.ecom.product_service.dto.ProductResponseDTO;
import com.ecom.product_service.entity.Category;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.repository.CategoryRepository;
import com.ecom.product_service.repository.ProductRepository;
import com.ecom.product_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {

        Category category=categoryRepository.findById(productRequestDTO.getCategoryId())
                .orElseThrow(()->new RuntimeException("Category Not Found"));
        Product product=new Product();
        product.setName(productRequestDTO.getName());
        product.setDescriptions(productRequestDTO.getDescription());
        product.setPrice(productRequestDTO.getPrice());
        product.setStockQuantity(productRequestDTO.getStockQuantity());
        product.setCategory(category);
        Product savedProduct=productRepository.save(product);
        return convertToDTO(savedProduct);

    }

    @Override
    public ProductResponseDTO getProductById(String productId) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product Not Found"));
        return convertToDTO(product);
    }

    @Override
    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO updateStock(String productId, Integer stockQuantity) {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product Not Found "));
       int newStock=product.getStockQuantity()+stockQuantity;
        product.setStockQuantity(newStock);
        productRepository.save(product);
        return convertToDTO(product);
    }

    @Override
    public String deleteProduct(String productId)
    {
        Product product=productRepository.findById(productId)
                .orElseThrow(()->new RuntimeException("Product Not Found"));
        productRepository.delete(product);
        return "Product"+ productId +"Deleted Successfully";
    }



    private ProductResponseDTO convertToDTO(Product product)
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


}
