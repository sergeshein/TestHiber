package com.example.testhiber.service;

import com.example.testhiber.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ProductService {
    List<ProductDto>getAll();
    void addToUserBucket(Long productId, String username);
}
