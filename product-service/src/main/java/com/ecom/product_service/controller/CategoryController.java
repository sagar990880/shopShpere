package com.ecom.product_service.controller;

import com.ecom.product_service.dto.CategoryResponseDTO;
import com.ecom.product_service.dto.CategoryRequestDTO;
import com.ecom.product_service.dto.ExtendedCategoryResponseDTO;
import com.ecom.product_service.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService)
    {
        this.categoryService=categoryService;
    }
    @PostMapping
    public CategoryResponseDTO createCategory(@RequestBody CategoryRequestDTO categoryRequestDTO)
    {
        return categoryService.createCategory(categoryRequestDTO);
    }

    @GetMapping
    public List<ExtendedCategoryResponseDTO>getAllCategories()
    {
        return categoryService.getAllCategories();
    }

    @PutMapping("/{categoryId}/update")
    public CategoryResponseDTO updateCategory(@PathVariable String categoryId,@RequestBody CategoryRequestDTO categoryRequestDTO)
    {
        return categoryService.updateCategory(categoryId,categoryRequestDTO);
    }

    @GetMapping("/{categoryId}")
        public ExtendedCategoryResponseDTO getCategoryById(@PathVariable String categoryId)
        {
            return categoryService.getCategoryById(categoryId);
        }
        @DeleteMapping("/{categoryId}")
        public void deleteCategory(@PathVariable String categoryId)
        {
            categoryService.deleteCategory(categoryId);
        }
}
