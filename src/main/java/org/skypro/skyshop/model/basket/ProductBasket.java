package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ProductBasket {

    private final Map<UUID, Integer> products = new HashMap<>(); // Поле для хранения продуктов и их количества

    // Метод добавления продукта в корзину
    public void addProduct(UUID id) {
        products.put(id, products.getOrDefault(id, 0) + 1);
    }

    // Метод получения всех продуктов в корзине
    public Map<UUID, Integer> getProducts() {
        return Collections.unmodifiableMap(products);
    }
}
