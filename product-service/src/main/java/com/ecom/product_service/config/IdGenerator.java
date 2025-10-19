package com.ecom.product_service.config;

import lombok.Synchronized;
import org.hibernate.annotations.Synchronize;

public class IdGenerator {
    private static int categoryIdCounter=0;
    private static int productIdCounter=0;

    public static synchronized int getNextProductId()
    {
        productIdCounter++;
        return productIdCounter;
    }

    public static synchronized int getNextCategoryId()
    {
        categoryIdCounter++;
        return categoryIdCounter;
    }
}
