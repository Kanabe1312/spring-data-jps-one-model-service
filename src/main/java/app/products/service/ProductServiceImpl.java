package app.products.service;

import app.products.dto.OrderLineRequest;
import app.products.dto.ProductCreateRequest;
import app.products.dto.ProductListResponse;
import app.products.dto.ProductPageResponse;
import app.products.dto.ProductResponse;
import app.products.model.Category;
import app.products.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void replaceAll(List<ProductCreateRequest> requests) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<ProductResponse> findById(UUID id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsById(UUID id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductResponse add(ProductCreateRequest request) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductResponse getBySku(String sku) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean existsBySku(String sku) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByCategory(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long countByCategory(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByNameContainingIgnoreCase(String text) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByPriceBetween(BigDecimal min, BigDecimal max) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByStockLessThan(int stock) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByActiveTrue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByCategoryAndActiveTrue(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByCategoryIn(List<Category> categories) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByAddedOnAfter(LocalDate date) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductResponse getMostExpensive(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long countByActiveTrueAndStockLessThan(int stock) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByAddedOnBetweenOrderByName(LocalDate from, LocalDate to) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Double averagePrice(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findByCategoryOrderByPriceDesc(Category category) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findTop3ByOrderByPriceDesc() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findAllSortedByName() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findAllSortedByStockDesc() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findInStockUpTo(BigDecimal maxPrice) {
        throw new UnsupportedOperationException();
    }

    @Override
    public BigDecimal stockValue() {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductListResponse findBySkuPrefix(String prefix) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void changePrice(String sku, BigDecimal price) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int deactivateOutOfStock() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteBySku(String sku) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long deleteInactive() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void sell(String sku, int quantity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void placeOrder(List<OrderLineRequest> lines) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductPageResponse findAllPage(int page, int size) {
        throw new UnsupportedOperationException();
    }

    @Override
    public ProductPageResponse findActivePage(int page, int size) {
        throw new UnsupportedOperationException();
    }
}
