package com.ys.customannotations.mapper;

import com.ys.customannotations.dto.response.ProductResDto;
import com.ys.customannotations.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductResDto toDto(Product product);
}
