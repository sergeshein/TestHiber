package com.example.testhiber.dto;

import com.example.testhiber.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BucketDetailDto {
    private String title;
    private Long productId;
    private BigDecimal price;
    private BigDecimal amount;
    private Double sum;

    public BucketDetailDto(Product product) {
        this.title = product.getTittle();
        this.productId = product.getId();
        this.price = product.getPrice();
        this.amount = new BigDecimal(1);
        this.sum = Double.valueOf(product.getPrice().toString());
    }
}
