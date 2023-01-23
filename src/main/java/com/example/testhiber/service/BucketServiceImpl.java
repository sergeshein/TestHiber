package com.example.testhiber.service;

import com.example.testhiber.entity.Bucket;
import com.example.testhiber.entity.Product;
import com.example.testhiber.entity.User;
import com.example.testhiber.repository.BucketRepo;
import com.example.testhiber.repository.ProductRepo;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        Bucket bucket = new Bucket();
        bucket.setUser(user);
        List<Product>products = getCollectRefProductsByIds(productIds);
        bucket.setProducts(products);
        return bucketRepo.save(bucket);
    }

    private List<Product> getCollectRefProductsByIds(List<Long> productIds) {
        return productIds.stream()
                .map(productRepo::getOne)
                .collect(Collectors.toList());
    }

    @Override
    public void addProduct(Bucket bucket, List<Long> productIds) {
        List<Product>products = bucket.getProducts();
        List<Product>newProductList = products == null ? new ArrayList<>() : new ArrayList<>(products);
        newProductList.addAll(getCollectRefProductsByIds(productIds));
        bucket.setProducts(newProductList);
        bucketRepo.save(bucket);

    }
}
