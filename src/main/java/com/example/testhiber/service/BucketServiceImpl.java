package com.example.testhiber.service;

import com.example.testhiber.dto.BucketDetailDto;
import com.example.testhiber.dto.BucketDto;
import com.example.testhiber.entity.Bucket;
import com.example.testhiber.entity.Product;
import com.example.testhiber.entity.User;
import com.example.testhiber.repository.BucketRepo;
import com.example.testhiber.repository.ProductRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class BucketServiceImpl implements BucketService{
    private final ProductRepo productRepo;
    private final BucketRepo bucketRepo;
    private final UserService userService;

    public BucketServiceImpl(ProductRepo productRepo, BucketRepo bucketRepo, UserService userService) {
        this.productRepo = productRepo;
        this.bucketRepo = bucketRepo;
        this.userService = userService;
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
                .map(productRepo::getOne)//getOne вытаскивает ссылку на обьект,
                                        // findById вытаскивает сам обьект
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

    @Override
    public BucketDto getBucketByUser(String name) {
        User user = userService.findByName(name);
        if (user == null || user.getBucket() == null){
            return new BucketDto();
        }

        BucketDto bucketDto = new BucketDto();
        Map<Long, BucketDetailDto>mapByProductId = new HashMap<>();

        List<Product> products = user.getBucket().getProducts();
        for(Product product : products){
            BucketDetailDto detailDto = mapByProductId.get(product.getId());
            if (detailDto == null){
                mapByProductId.put(product.getId(), new BucketDetailDto(product));
            }else {
                detailDto.getAmount().add(new BigDecimal(1.0));
                detailDto.setSum(detailDto.getSum() + Double.valueOf(product.getPrice().toString()));
            }
        }
        bucketDto.setBucketDetailDtoList(new ArrayList<>(mapByProductId.values()));
        //
        bucketDto.aggregate();

        return bucketDto;
    }
}
