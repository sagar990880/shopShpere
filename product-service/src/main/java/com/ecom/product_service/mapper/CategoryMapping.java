package com.ecom.product_service.mapper;

import com.ecom.product_service.dto.CategoryResponseDTO;
import com.ecom.product_service.entity.Category;

import java.util.Locale;

public class CategoryMapping {

    public static CategoryResponseDTO toCategoryResponseDTO(Category category)
    {
        CategoryResponseDTO categoryResponseDTO=new CategoryResponseDTO();
        categoryResponseDTO.setCategoryId(category.getCategoryId());
        categoryResponseDTO.setName(category.getName());
        categoryResponseDTO.setDescription(category.getDescription());
        return categoryResponseDTO;
    }

    public static Category toCategoryEntity(CategoryResponseDTO categoryResponseDTO)
    {
        Category category=new Category();
        category.setCategoryId(categoryResponseDTO.getCategoryId());
        category.setName(categoryResponseDTO.getName());
        category.setDescription(category.getDescription());
        return category;
    }
}
