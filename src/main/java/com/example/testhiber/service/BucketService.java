package com.example.testhiber.service;

import com.example.testhiber.entity.Bucket;
import com.example.testhiber.entity.User;

import java.util.List;

public interface BucketService {
    Bucket createBucket(User user, List<Long>productIds);
    void addProduct(Bucket bucket, List<Long>productIds);
}
