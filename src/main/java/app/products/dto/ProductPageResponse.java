package app.products.dto;

import java.util.List;

public record ProductPageResponse(
        List<ProductResponse> products,
        int page,
        int totalPages,
        long totalElements,
        boolean hasNext) {
}
