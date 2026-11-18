package app.products.dto;

import app.products.model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ProductCreateRequest(
        String name,
        String sku,
        Category category,
        BigDecimal price,
        int stock,
        boolean active,
        LocalDate addedOn) {
}
