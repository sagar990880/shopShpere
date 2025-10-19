package com.ecom.product_service.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExtendedCategoryResponseDTO extends CategoryResponseDTO{
    List<ProductResponseDTO>products;

    public ExtendedCategoryResponseDTO(String categoryId,String name,String description,List<ProductResponseDTO>products)
    {
        super(categoryId,name,description);
        this.products=products;
    }
}
