package com.example.testhiber.controller;

import com.example.testhiber.dto.ProductDto;
import com.example.testhiber.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String List(Model model){
        List<ProductDto>productDtoList= productService.getAll();
        model.addAttribute("products", productDtoList);
        return "products";
    }
}
