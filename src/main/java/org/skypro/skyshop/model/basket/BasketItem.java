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

    // Метод для получения стоимости товара с учетом типа продукта
    public double getTotalPrice() {
        // В зависимости от типа продукта вычисляем цену
        if (product instanceof DiscountedProduct) {
            return ((DiscountedProduct) product).getPriceProduct() * quantity;
        } else if (product instanceof FixPriceProduct) {
            return ((FixPriceProduct) product).getPriceProduct() * quantity;
        } else if (product instanceof SimpleProduct) {
            return ((SimpleProduct) product).getPriceProduct() * quantity;
        }
        return 0; // Если продукт не из этих типов, возвращаем 0
    }
}