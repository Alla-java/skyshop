package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product {
    private static final int FIXED_PRICE = 500;

    public FixPriceProduct(String nameProduct) {
        super(UUID.randomUUID(), nameProduct);
    }

    @Override
    public int getPriceProduct() {
        return FIXED_PRICE;
    }

    @Override
    public boolean isSpecial() {
        return true; // Товары с фиксированной ценой считаются специальными
    }

    @Override
    public String toString() {
        return getNameProduct() + ": Фиксированная цена " + FIXED_PRICE;
    }
}
