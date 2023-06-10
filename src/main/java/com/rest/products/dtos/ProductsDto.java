package com.rest.products.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductsDto(@NotBlank String name, @NotNull BigDecimal value) {
}
