package org.skypro.skyshop.model.product;

import java.util.UUID;

public class SimpleProduct extends Product {
    private int priceProduct;

    public SimpleProduct(String nameProduct, int priceProduct) {
        super(UUID.randomUUID(), nameProduct); // Передача имени в конструктор родительского класса
        if (priceProduct <= 0) {
            throw new IllegalArgumentException("priceProduct должна быть больше 0");
        }
        this.priceProduct = priceProduct;
    }

    @Override
    public int getPriceProduct() {
        return this.priceProduct;
    }

    @Override
    public String toString() {
        return getNameProduct() + ": " + getPriceProduct();
    }
}
