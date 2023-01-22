package com.example.testhiber.service;

import com.example.testhiber.dto.ProductDto;
import com.example.testhiber.mapper.ProductMapper;
import com.example.testhiber.repository.ProductRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ProductRepo productRepo;

    public ProductServiceImpl(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }


    @Override
    public List<ProductDto> getAll() {
        return mapper.fromProductDtoList(productRepo.findAll());
    }
}
