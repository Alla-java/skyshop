package org.skypro.skyshop.service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.exceptions.NoSuchProductException;
import org.springframework.stereotype.Service;

import org.skypro.skyshop.model.search.Searchable;

@Service
public class StorageService {

    // Хранилище продуктов
    private final Map<UUID, Product> productStorage;

    // Хранилище статей
    private final Map<UUID, Article> articleStorage;

    // Конструктор, который инициализирует оба хранилища
    public StorageService() {
        this.productStorage = new HashMap<>();
        this.articleStorage = new HashMap<>();
        fillProductStorage(); //метод для заполнения хранилища продуктов
        fillArticleStorage(); //метод для заполнения хранилища статей
    }

    private void fillProductStorage() {

        Product productTomatoes = new SimpleProduct("Помидоры на ветке", 229);
        Product productСucumbers = new DiscountedProduct("Огурцы", 123, 10);
        Product productСhicken = new SimpleProduct("Куриное филе", 329);
        Product productFarsh = new FixPriceProduct("Фарш говяжий");

        // Добавляем продукты с зафиксированными UUID
        productStorage.put(productTomatoes.getId(),productTomatoes);
        productStorage.put(productСucumbers.getId(),productСucumbers);
        productStorage.put(productСhicken.getId(),productСhicken);
        productStorage.put(productFarsh.getId(),productFarsh);
    }

    // Приватный метод для заполнения хранилища статей
    private void fillArticleStorage() {
        articleStorage.put(UUID.randomUUID(), new Article("фасоль: польза и вред для организма", "Текст статьи про шакшуку"));
        articleStorage.put(UUID.randomUUID(), new Article("Рыбы Черного моря: гид от шеф-повара", "Текст статьи про рыб"));
        articleStorage.put(UUID.randomUUID(), new Article("Чем полезна жимолость и как ее готовить", "Текст статьи про жимолость"));
        articleStorage.put(UUID.randomUUID(), new Article("Красная или белая фасоль: что полезнее", "Текст статьи про фасоль"));
    }

    // Геттеры для доступа к хранилищам
    public Map<UUID, Product> getProductStorage() {
        return productStorage;
    }

    public Map<UUID, Article> getArticleStorage() {
        return articleStorage;
    }

    // Методы для получения продукта и статьи по UUID
    public Product getProduct(UUID id) {
        // Проверяем, существует ли продукт с данным ID
        Product product = productStorage.get(id);
        if (product == null) {
            throw new NoSuchProductException("Продукт с ID " + id + " не найден");
        }
        return product;
    }

    public Article getArticle(UUID id) {
        return articleStorage.get(id);
    }

    // Дополнительные методы для получения всех продуктов и статей
    public Map<UUID, Product> getAllProducts() {
        return productStorage;
    }

    public Map<UUID, Article> getAllArticles() {
        return articleStorage;
    }

    // Метод для получения объединенной коллекции всех объектов Searchable (продуктов и статей)
    public Collection<Searchable> getAllSearchables() {
        // Объединяем коллекции продуктов и статей в одну
        return Stream.concat(
                productStorage.values().stream(),    // Поток продуктов
                articleStorage.values().stream()     // Поток статей
        ).collect(Collectors.toList());           // Собираем в список
    }

    public Optional<Product> getProductById(UUID id) {
        // Если продукт не найден, выбрасываем кастомное исключение NoSuchProductException
        return Optional.ofNullable(Optional.ofNullable(productStorage.get(id))
                .orElseThrow(() -> new NoSuchProductException("Продукт с ID " + id + " не найден")));
    }
}
