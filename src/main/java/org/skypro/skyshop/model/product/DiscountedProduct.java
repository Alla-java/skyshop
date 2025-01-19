package org.skypro.skyshop.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice; // Базовая цена товара
    private int discountPercentage; // Скидка в процентах

    public DiscountedProduct(String nameProduct, int basePrice, int discountPercentage) {
        super(UUID.randomUUID(), nameProduct); // Передаем имя в конструктор родительского класса
        if (basePrice <= 0) {
            throw new IllegalArgumentException("basePrice должна быть строго больше 0");
        }
        if (discountPercentage < 0 || discountPercentage > 100) {
            throw new IllegalArgumentException("discountPercentage должна быть в пределах от 0 до 100%");
        }
        this.basePrice = basePrice;
        this.discountPercentage = discountPercentage;
    }

    // Переопределяем метод getPriceProduct с учетом скидки
    @Override
    public int getPriceProduct() {
        return basePrice - (basePrice * discountPercentage / 100);
    }

    @Override
    public boolean isSpecial() {
        return true; // Товары с скидкой считаются специальными
    }

    @Override
    public String toString() {
        return getNameProduct() + ": " + getPriceProduct() + " (" + discountPercentage + "%)";
    }
}
