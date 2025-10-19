package com.ecom.product_service.service;

import com.ecom.product_service.dto.CategoryResponseDTO;
import com.ecom.product_service.dto.CategoryRequestDTO;
import com.ecom.product_service.dto.ExtendedCategoryResponseDTO;
import com.ecom.product_service.entity.Category;

import java.util.List;

public interface CategoryService {
    CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO);
    ExtendedCategoryResponseDTO getCategoryById(String categoryId);
    List<ExtendedCategoryResponseDTO>getAllCategories();
    CategoryResponseDTO updateCategory(String categoryId,CategoryRequestDTO categoryRequestDTO);
    void deleteCategory(String categoryId);
}
