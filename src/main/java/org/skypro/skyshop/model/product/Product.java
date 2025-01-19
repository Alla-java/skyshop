package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private final String nameProduct;

    public Product(UUID id, String nameProduct) {
        if (id == null) {
            throw new IllegalArgumentException("UUID не может быть null");
        }
        if (nameProduct == null || nameProduct.isBlank()) {
            throw new IllegalArgumentException("nameProduct не может быть null, пустым или состоять только из пробелов");
        }
        this.id = id;
        this.nameProduct = nameProduct;
    }

    // Геттер для id
    public UUID getId() {
        return id;
    }

    public String getNameProduct() {
        return this.nameProduct;
    }

    public abstract int getPriceProduct();

    // Абстрактный метод, который определяет, является ли товар специальным
    public boolean isSpecial() {
        return false; // По умолчанию товар не специальный
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(id, product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override @JsonIgnore
    public String getSearchTerm() {
        return getNameProduct();
    }

    @Override
    public String getName() {
        return getNameProduct();
    }

    @Override
    public String getStringRepresentation() {
        return getNameProduct() + " — PRODUCT";
    }

    @Override
    public abstract String toString();

}
