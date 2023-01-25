package com.example.testhiber.repository;

import com.example.testhiber.entity.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BucketRepo extends JpaRepository<Bucket, Long> {
}
