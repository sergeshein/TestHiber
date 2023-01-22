package com.example.testhiber.service;

import com.example.testhiber.entity.Bucket;
import com.example.testhiber.entity.User;
import com.example.testhiber.repository.BucketRepo;
import com.example.testhiber.repository.ProductRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class BucketServiceImpl implements BucketService{
    private final ProductRepo productRepo;
    private final BucketRepo bucketRepo;

    public BucketServiceImpl(ProductRepo productRepo, BucketRepo bucketRepo) {
        this.productRepo = productRepo;
        this.bucketRepo = bucketRepo;
    }

    @Override
    @Transactional
    public Bucket createBucket(User user, List<Long> productIds) {
        return null;
    }

    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {

    }
}
