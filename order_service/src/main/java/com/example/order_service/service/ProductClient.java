package com.example.order_service.service;

import com.example.order_service.dto.ProductResponseDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;

    public ProductClient(RestTemplate restTemplate)
    {

        this.restTemplate=restTemplate;
    }

    public ProductResponseDTO getProductName(String productId)
    {
        String url="http://product-service/products/"+productId;
        return restTemplate.getForObject(url, ProductResponseDTO.class);
    }
    public void updateStock(String productId,int quantity)
    {
        String url="http://product-service/products/"+productId+"/stock?stockQuantity="+quantity;
        restTemplate.put(url,null);
    }
}
