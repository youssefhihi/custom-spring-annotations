package com.ys.customannotations.controller;

import com.ys.customannotations.customAnnotation.PreventDuplicate.PreventDuplicate;
import com.ys.customannotations.dto.request.ProductReqDto;
import com.ys.customannotations.dto.response.ProductResDto;
import com.ys.customannotations.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@Validated
@Slf4j
public class ProductController {

    private final ProductService productService;

    // Create a product
    @PostMapping
    @PreventDuplicate(expiration = 6 * 1000)
    public ResponseEntity<ProductResDto> createProduct(@Valid @RequestBody ProductReqDto productReqDto) {
        ProductResDto productResDto = productService.createProduct(productReqDto);
        return new ResponseEntity<>(productResDto, HttpStatus.CREATED);
    }

    // Get all products
    @GetMapping
    @PreventDuplicate(expiration = 1)
    public ResponseEntity<List<ProductResDto>> getAllProducts() {
        List<ProductResDto> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
