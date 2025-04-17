package com.ys.customannotations.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record ProductReqDto(
        @NotBlank(message = "Product name must not be blank")
        @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
        String name,

        @Positive(message = "Price must be greater than zero")
        Double price,

        @Size(max = 255, message = "Description can't be longer than 255 characters")
        String description
) {
}
