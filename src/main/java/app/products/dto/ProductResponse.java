package app.products.dto;

import app.products.model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ProductResponse(
        UUID id,
        String name,
        String sku,
        Category category,
        BigDecimal price,
        int stock,
        boolean active,
        LocalDate addedOn) {
}
