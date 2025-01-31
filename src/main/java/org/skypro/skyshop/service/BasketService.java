package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.service.exceptions.NoSuchProductException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    // Конструктор для инъекции зависимостей
    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    // Метод добавления товара в корзину по ID
    public void addProductToBasket(UUID id) {
        // Получаем продукт из StorageService по ID
        Optional<Product> productOptional = storageService.getProductById(id);

        // Логируем UUID продукта
        System.out.println("По запросу ищем продукт с ID: " + id);

        // Если продукт не найден, выбрасываем исключение
        if (!productOptional.isPresent()) {
            throw new NoSuchProductException("Product с таким id:" + id + "не найден");
        }

        // Добавляем продукт в корзину, если он найден
        Product product = productOptional.get();
        productBasket.addProduct(id);
    }

    //Метод отображения корзины пользователю
    public UserBasket getUserBasket() {
        // Получаем все товары из корзины и их количества
        List<BasketItem> basketItems = productBasket.getProducts().entrySet().stream()
                .map(entry -> new BasketItem(storageService.getProduct(entry.getKey()), entry.getValue()))
                .collect(Collectors.toList());

        // Создаем объект UserBasket и возвращаем его
        return new UserBasket(basketItems);
    }
}
