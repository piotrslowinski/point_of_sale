package com.piotrslowinski.impaq.model.repositories;

import com.piotrslowinski.impaq.model.Product;

import java.util.Optional;

public interface ProductRepository {

    void save(Product product);

    Optional<Product> get(String code);
}
