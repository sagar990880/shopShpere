package com.ecom.product_service.controller;

import com.ecom.product_service.dto.ProductRequestDTO;
import com.ecom.product_service.dto.ProductResponseDTO;
import com.ecom.product_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO productRequestDTO)
    {
        return productService.createProduct(productRequestDTO);
    }

    @GetMapping("/{productId}")
    public ProductResponseDTO getProductById(@PathVariable String productId)
    {
        return productService.getProductById(productId);
    }

    @GetMapping
    public List<ProductResponseDTO> getAllProducts()
    {
        return productService.getAllProducts();
    }

    @PutMapping("/{productId}/stock")
    public ProductResponseDTO updateStock(@PathVariable String productId,@RequestParam Integer stockQuantity)
    {
        return productService.updateStock(productId,stockQuantity);
    }

    @DeleteMapping("/{productId}")
    public String deleteProduct(@PathVariable String productId)
    {
        return productService.deleteProduct(productId);
    }
}
