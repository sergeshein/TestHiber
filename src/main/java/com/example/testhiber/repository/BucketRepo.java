package com.example.testhiber.repository;

import com.example.testhiber.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepo extends JpaRepository<Bucket, Long> {
}
