package com.ecom.product_service.service.impl;

import com.ecom.product_service.dto.CategoryResponseDTO;
import com.ecom.product_service.dto.CategoryRequestDTO;
import com.ecom.product_service.dto.ExtendedCategoryResponseDTO;
import com.ecom.product_service.entity.Category;
import com.ecom.product_service.entity.Product;
import com.ecom.product_service.mapper.CategoryMapping;
import com.ecom.product_service.mapper.ProductMapping;
import com.ecom.product_service.repository.CategoryRepository;
import com.ecom.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO createCategory(CategoryRequestDTO categoryRequestDTO) {
        Category category=new Category();
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        Category savedCategory=categoryRepository.save(category);
        return CategoryMapping.toCategoryResponseDTO(savedCategory);
    }

    @Override
    public ExtendedCategoryResponseDTO getCategoryById(String categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Category not found"));
        return convertToExtendedCategoryResponseDTO(category);
    }

    @Override
    public List<ExtendedCategoryResponseDTO> getAllCategories() {
        List<Category> categoryList=categoryRepository.findAll();
        List<ExtendedCategoryResponseDTO>categoryResponseDTOS=new ArrayList<>();
        for(Category category: categoryList)
        {
            ExtendedCategoryResponseDTO categoryResponseDTO=convertToExtendedCategoryResponseDTO(category);
            categoryResponseDTOS
                    .add(categoryResponseDTO);
        }
        return categoryResponseDTOS;

    }

    @Override
    public CategoryResponseDTO updateCategory(String categoryId, CategoryRequestDTO categoryRequestDTO) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Category Not Found"));
        category.setName(categoryRequestDTO.getName());
        category.setDescription(categoryRequestDTO.getDescription());
        Category updateCategory= categoryRepository.save(category);
        return CategoryMapping.toCategoryResponseDTO(updateCategory);

    }

    @Override
    public void deleteCategory(String categoryId) {
        Category category=categoryRepository.findById(categoryId)
                .orElseThrow(()->new RuntimeException("Category Not Found"));
        categoryRepository.delete(category);

    }


    private ExtendedCategoryResponseDTO convertToExtendedCategoryResponseDTO(Category category)
    {
        List<Product> productList=category.getProducts();
        return new ExtendedCategoryResponseDTO(category.getCategoryId(),category.getName(),category.getDescription(),
                productList.stream().map(ProductMapping::toProductResponseDTO).toList());

    }
}
