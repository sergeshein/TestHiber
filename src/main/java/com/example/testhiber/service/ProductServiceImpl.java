package com.example.testhiber.service;

import com.example.testhiber.dto.ProductDto;
import com.example.testhiber.entity.Bucket;
import com.example.testhiber.entity.User;
import com.example.testhiber.mapper.ProductMapper;
import com.example.testhiber.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper mapper = ProductMapper.MAPPER;
    private final ProductRepo productRepo;
    private final UserService userService;
    private final BucketService bucketService;


    public ProductServiceImpl(ProductRepo productRepo, UserService userService, BucketService bucketService) {
        this.productRepo = productRepo;
        this.userService = userService;
        this.bucketService = bucketService;
    }


    @Override
    public List<ProductDto> getAll() {
        return mapper.fromProductDtoList(productRepo.findAll());
    }

    @Override
    @Transactional
    public void addToUserBucket(Long productId, String username) {
        User user = userService.findByName(username);
        if(user == null){
            throw new RuntimeException("user not found" + username);
        }

        Bucket bucket = user.getBucket();
        if(bucket == null){
            Bucket newBucket = bucketService.createBucket(user, Collections.singletonList(productId));
            user.setBucket(newBucket);
            userService.save(user);
        }else {
            bucketService.addProduct(bucket, Collections.singletonList(productId));
        }

    }
}
