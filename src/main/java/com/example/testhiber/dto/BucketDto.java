package com.example.testhiber.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BucketDto {
    private int amountProducts;
    private Double sum;
    private List<BucketDetailDto> bucketDetailDtoList = new ArrayList<>();

    public void aggregate(){
        this.amountProducts = bucketDetailDtoList.size();
        this.sum = bucketDetailDtoList.stream()
                .map(BucketDetailDto::getSum)
                .mapToDouble(Double::doubleValue)
                .sum();
    }
}
