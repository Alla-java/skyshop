package org.skypro.skyshop.model.basket;

import java.util.Collection;
import java.util.List;

public final class UserBasket {
    private final List<BasketItem> basketItems; // Список элементов корзины
    private final double total; // Общая стоимость корзины

    // Конструктор принимает список товаров в корзине
    public UserBasket(List<BasketItem> basketItems) {
        this.basketItems = basketItems;

        // Считаем общую стоимость корзины с использованием StreamAPI
        this.total = basketItems.stream()
                .mapToDouble(p -> p.getProduct().getPriceProduct() * p.getQuantity()) // Преобразуем каждый элемент корзины в его стоимость
                .sum();                                 // Суммируем все стоимости
    }

    // Геттеры
    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public double getTotal() {
        return total;
    }

    public Collection<Object> getItems() {
        return null;
    }
}
