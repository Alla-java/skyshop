package org.skypro.skyshop.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
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

    // Приватный метод для заполнения хранилища продуктов
    private void fillProductStorage() {
        productStorage.put(UUID.randomUUID(), new SimpleProduct("Помидоры на ветке", 229));
        productStorage.put(UUID.randomUUID(), new DiscountedProduct("Огурцы", 123,10)); // 10% скидка
        productStorage.put(UUID.randomUUID(), new SimpleProduct("Куриное филе", 329));
        productStorage.put(UUID.randomUUID(), new FixPriceProduct("Фарш говяжий"));
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

    // ..Методы для добавления продуктов и статей в хранилища
    public void addProduct(Product product) {
        if (product != null) {
            productStorage.put(product.getId(), product);
        }
    }

    public void addArticle(Article article) {
        if (article != null) {
            articleStorage.put(article.getId(), article);
        }
    }

    // ..Методы для получения продукта и статьи по UUID
    public Product getProduct(UUID id) {
        return productStorage.get(id);
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

    // ..Пример методов для удаления продуктов и статей
    public void removeProduct(UUID id) {
        productStorage.remove(id);
    }

    public void removeArticle(UUID id) {
        articleStorage.remove(id);
    }

    // Метод для получения объединенной коллекции всех объектов Searchable (продуктов и статей)
    public Collection<Searchable> getAllSearchables() {
        // Объединяем коллекции продуктов и статей в одну
        return Stream.concat(
                productStorage.values().stream(),    // Поток продуктов
                articleStorage.values().stream()     // Поток статей
        ).collect(Collectors.toList());           // Собираем в список
    }
}
