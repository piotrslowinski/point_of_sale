package com.piotrslowinski.impaq.infrastructure;

import com.piotrslowinski.impaq.model.Product;
import com.piotrslowinski.impaq.model.repositories.ProductRepository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryProductRepository implements ProductRepository {

    private static final Map<String, Product> REPO;

    static {
        REPO = new HashMap<>();
        Product p1 = new Product("a", "milk", BigDecimal.valueOf(1.80));
        Product p2 = new Product("b", "bread", BigDecimal.valueOf(3.90));
        Product p3 = new Product("c", "water", BigDecimal.valueOf(1.59));

        REPO.put("a", p1);
        REPO.put("b", p2);
        REPO.put("c", p3);
    }


    @Override
    public void save(Product product) {
        REPO.put(product.getBarCode(), product);
    }

    @Override
    public Optional<Product> get(String code) {
        if (!REPO.containsKey(code)) {
            return Optional.empty();
        }
        return Optional.of(REPO.get(code));
    }
}
