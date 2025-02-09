package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.Product;

import java.util.Objects;

public final class BasketItem {
    private final Product product;  // Продукт
    private final int quantity;     // Количество

    // Конструктор для инициализации полей
    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // Геттеры
    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product, quantity);
    }
}