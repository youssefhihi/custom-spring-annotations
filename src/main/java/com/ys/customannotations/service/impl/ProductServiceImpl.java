package com.ys.customannotations.service.impl;

import com.ys.customannotations.customAnnotation.SmartLog.SmartLog;
import com.ys.customannotations.dto.request.ProductReqDto;
import com.ys.customannotations.dto.response.ProductResDto;
import com.ys.customannotations.entity.Product;
import com.ys.customannotations.mapper.ProductMapper;
import com.ys.customannotations.repository.ProductRepository;
import com.ys.customannotations.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;
    private final ProductMapper mapper;

    @Override
    @SmartLog("create service function")
    public ProductResDto createProduct(ProductReqDto dto) {
        Product product = Product.builder()
                .name(dto.name())
                .price(dto.price())
                .description(dto.description())
                .build();
        return mapper.toDto(repository.save(product));
    }

    @Override
    @SmartLog("getAll service function")
    public List<ProductResDto> getAllProducts() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }


}
