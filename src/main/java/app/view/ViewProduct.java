package app.view;

import app.products.dto.OrderLineRequest;
import app.products.dto.ProductCreateRequest;
import app.products.dto.ProductListResponse;
import app.products.dto.ProductPageResponse;
import app.products.dto.ProductResponse;
import app.products.exceptions.InsufficientStockException;
import app.products.exceptions.ProductAlreadyExistsException;
import app.products.exceptions.ProductNotFoundException;
import app.products.model.Category;
import app.products.service.ProductService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class ViewProduct {

    private final ProductService productService;

    public ViewProduct(ProductService productService) {
        this.productService = productService;
    }

    public void start() {
        populeaza();
        citeste();
        valideaza();
        derivateGhidate();
        derivatePractica();
        provocari();
        sorteaza();
        interogari();
        modifica();
        sterge();
        comanda();
        pagineaza();
    }

    private void populeaza() {
        productService.replaceAll(List.of(
                new ProductCreateRequest("Espresso", "COF-0001", Category.COFFEE, new BigDecimal("9.50"), 120, true, LocalDate.of(2026, 1, 10)),
                new ProductCreateRequest("Cappuccino", "COF-0002", Category.COFFEE, new BigDecimal("13.00"), 80, true, LocalDate.of(2026, 1, 10)),
                new ProductCreateRequest("Flat White", "COF-0003", Category.COFFEE, new BigDecimal("14.50"), 0, true, LocalDate.of(2026, 3, 2)),
                new ProductCreateRequest("Green Tea", "TEA-0001", Category.TEA, new BigDecimal("11.00"), 40, true, LocalDate.of(2026, 2, 15)),
                new ProductCreateRequest("Black Tea", "TEA-0002", Category.TEA, new BigDecimal("10.00"), 4, true, LocalDate.of(2026, 2, 15)),
                new ProductCreateRequest("Croissant", "PAS-0001", Category.PASTRY, new BigDecimal("8.00"), 25, true, LocalDate.of(2026, 4, 1)),
                new ProductCreateRequest("Cheesecake", "PAS-0002", Category.PASTRY, new BigDecimal("18.50"), 3, true, LocalDate.of(2026, 5, 20)),
                new ProductCreateRequest("Brownie", "PAS-0003", Category.PASTRY, new BigDecimal("12.00"), 0, true, LocalDate.of(2026, 5, 20)),
                new ProductCreateRequest("French Press", "EQP-0001", Category.EQUIPMENT, new BigDecimal("129.99"), 7, false, LocalDate.of(2026, 6, 11)),
                new ProductCreateRequest("Coffee Grinder", "EQP-0002", Category.EQUIPMENT, new BigDecimal("249.00"), 2, true, LocalDate.of(2026, 9, 30))
        ));
        System.out.println("Produse: " + productService.count());
    }

    private void citeste() {
        List<ProductResponse> toate = productService.findAll().products();
        toate.forEach(System.out::println);

        UUID id = toate.get(0).id();
        Optional<ProductResponse> gasit = productService.findById(id);
        System.out.println("Gasit: " + gasit.get().name());

        UUID altId = UUID.fromString("00000000-0000-0000-0000-000000000000");
        System.out.println("Exista: " + productService.existsById(altId));
        if (productService.findById(altId).isEmpty()) {
            System.out.println("Produs inexistent");
        }
    }

    private void valideaza() {
        ProductCreateRequest gresit = new ProductCreateRequest(" ", "abc", Category.TEA, new BigDecimal("0.00"), -1, true, LocalDate.of(2026, 1, 10));
        try {
            productService.add(gresit);
        } catch (ConstraintViolationException e) {
            e.getConstraintViolations().stream()
                    .map(violation -> violation.getMessage())
                    .sorted()
                    .forEach(System.out::println);
        }

        ProductCreateRequest dublura = new ProductCreateRequest("Ristretto", "COF-0001", Category.COFFEE, new BigDecimal("9.00"), 10, true, LocalDate.of(2026, 1, 10));
        try {
            productService.add(dublura);
        } catch (ProductAlreadyExistsException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Produse: " + productService.count());
    }

    private void derivateGhidate() {
        System.out.println(productService.getBySku("TEA-0001").name());
        try {
            productService.getBySku("XXX-0000");
        } catch (ProductNotFoundException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(productService.existsBySku("COF-0002"));
        afiseaza(productService.findByCategory(Category.PASTRY));
        System.out.println(productService.countByCategory(Category.COFFEE));
    }

    private void derivatePractica() {
        afiseaza(productService.findByNameContainingIgnoreCase("TEA"));
        afiseaza(productService.findByPriceBetween(new BigDecimal("10.00"), new BigDecimal("13.00")));
        afiseaza(productService.findByStockLessThan(5));
        afiseaza(productService.findByActiveTrue());
        afiseaza(productService.findByCategoryAndActiveTrue(Category.EQUIPMENT));
        afiseaza(productService.findByCategoryIn(List.of(Category.COFFEE, Category.TEA)));
        afiseaza(productService.findByAddedOnAfter(LocalDate.of(2026, 5, 1)));
    }

    private void provocari() {
        System.out.println(productService.getMostExpensive(Category.PASTRY).name());
        System.out.println(productService.countByActiveTrueAndStockLessThan(5));
        afiseaza(productService.findByAddedOnBetweenOrderByName(LocalDate.of(2026, 5, 1), LocalDate.of(2026, 5, 31)));
        System.out.println(productService.averagePrice(Category.TEA));
    }

    private void sorteaza() {
        afiseaza(productService.findByCategoryOrderByPriceDesc(Category.COFFEE));
        afiseaza(productService.findTop3ByOrderByPriceDesc());
        afiseaza(productService.findAllSortedByName());
        afiseaza(productService.findAllSortedByStockDesc());
    }

    private void interogari() {
        afiseaza(productService.findInStockUpTo(new BigDecimal("12.00")));
        System.out.println(productService.stockValue());
        afiseaza(productService.findBySkuPrefix("PAS"));
    }

    private void modifica() {
        productService.changePrice("COF-0001", new BigDecimal("10.00"));
        System.out.println(productService.getBySku("COF-0001").price());
        System.out.println("Produse: " + productService.count());

        System.out.println("Dezactivate: " + productService.deactivateOutOfStock());
        afiseaza(productService.findByActiveTrue());
    }

    private void sterge() {
        productService.deleteBySku("TEA-0002");
        System.out.println(productService.existsBySku("TEA-0002"));

        System.out.println("Sterse: " + productService.deleteInactive());
        System.out.println("Produse: " + productService.count());
    }

    private void comanda() {
        plaseaza(List.of(new OrderLineRequest("COF-0002", 2), new OrderLineRequest("PAS-0002", 5)));
        System.out.println("Stoc COF-0002: " + productService.getBySku("COF-0002").stock());
        plaseaza(List.of(new OrderLineRequest("COF-0002", 2), new OrderLineRequest("PAS-0001", 3)));
        System.out.println("Stoc COF-0002: " + productService.getBySku("COF-0002").stock());
        System.out.println("Stoc PAS-0001: " + productService.getBySku("PAS-0001").stock());
    }

    private void plaseaza(List<OrderLineRequest> linii) {
        try {
            productService.placeOrder(linii);
            System.out.println("Comanda plasata");
        } catch (InsufficientStockException e) {
            System.out.println("Comanda respinsa: " + e.getMessage());
        }
    }

    private void pagineaza() {
        ProductPageResponse pagina = productService.findAllPage(1, 4);
        afiseaza(pagina.products());
        System.out.println(pagina.totalElements() + " produse, " + pagina.totalPages() + " pagini");

        ProductPageResponse active = productService.findActivePage(0, 3);
        System.out.println("Catalog: " + active.totalElements() + " produse active");
        afiseazaPagina(active);
        while (active.hasNext()) {
            active = productService.findActivePage(active.page() + 1, 3);
            afiseazaPagina(active);
        }
    }

    private void afiseazaPagina(ProductPageResponse pagina) {
        System.out.println("Pagina " + (pagina.page() + 1) + "/" + pagina.totalPages() + ": " + pagina.products().stream().map(ProductResponse::name).toList());
    }

    private void afiseaza(ProductListResponse lista) {
        afiseaza(lista.products());
    }

    private void afiseaza(List<ProductResponse> produse) {
        System.out.println(produse.stream().map(ProductResponse::name).toList());
    }
}
