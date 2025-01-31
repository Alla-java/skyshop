package org.skypro.skyshop.model.basket;

import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;

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
}