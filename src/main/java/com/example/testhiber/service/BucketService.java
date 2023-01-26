package com.example.testhiber.service;

import com.example.testhiber.dto.BucketDto;
import com.example.testhiber.entity.Bucket;
import com.example.testhiber.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface BucketService {
    Bucket createBucket(User user, List<Long>productIds);
    void addProduct(Bucket bucket, List<Long>productIds);
    BucketDto getBucketByUser(String name);
}
