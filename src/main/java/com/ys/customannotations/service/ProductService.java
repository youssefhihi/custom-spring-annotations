package com.ys.customannotations.service;

import com.ys.customannotations.dto.request.ProductReqDto;
import com.ys.customannotations.dto.response.ProductResDto;

import java.util.List;

public interface ProductService {
    ProductResDto createProduct(ProductReqDto product);
    List<ProductResDto> getAllProducts();
}
