package app.products.service;

import app.products.dto.OrderLineRequest;
import app.products.dto.ProductCreateRequest;
import app.products.dto.ProductListResponse;
import app.products.dto.ProductPageResponse;
import app.products.dto.ProductResponse;
import app.products.model.Category;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductService {

    void replaceAll(List<ProductCreateRequest> requests);

    long count();

    ProductListResponse findAll();

    Optional<ProductResponse> findById(UUID id);

    boolean existsById(UUID id);

    ProductResponse add(ProductCreateRequest request);

    ProductResponse getBySku(String sku);

    boolean existsBySku(String sku);

    ProductListResponse findByCategory(Category category);

    long countByCategory(Category category);

    ProductListResponse findByNameContainingIgnoreCase(String text);

    ProductListResponse findByPriceBetween(BigDecimal min, BigDecimal max);

    ProductListResponse findByStockLessThan(int stock);

    ProductListResponse findByActiveTrue();

    ProductListResponse findByCategoryAndActiveTrue(Category category);

    ProductListResponse findByCategoryIn(List<Category> categories);

    ProductListResponse findByAddedOnAfter(LocalDate date);

    ProductResponse getMostExpensive(Category category);

    long countByActiveTrueAndStockLessThan(int stock);

    ProductListResponse findByAddedOnBetweenOrderByName(LocalDate from, LocalDate to);

    Double averagePrice(Category category);

    ProductListResponse findByCategoryOrderByPriceDesc(Category category);

    ProductListResponse findTop3ByOrderByPriceDesc();

    ProductListResponse findAllSortedByName();

    ProductListResponse findAllSortedByStockDesc();

    ProductListResponse findInStockUpTo(BigDecimal maxPrice);

    BigDecimal stockValue();

    ProductListResponse findBySkuPrefix(String prefix);

    void changePrice(String sku, BigDecimal price);

    int deactivateOutOfStock();

    void deleteBySku(String sku);

    long deleteInactive();

    void sell(String sku, int quantity);

    void placeOrder(List<OrderLineRequest> lines);

    ProductPageResponse findAllPage(int page, int size);

    ProductPageResponse findActivePage(int page, int size);
}
