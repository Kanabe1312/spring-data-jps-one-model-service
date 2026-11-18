package app.products.dto;

import java.util.List;

public record ProductListResponse(List<ProductResponse> products) {
}
