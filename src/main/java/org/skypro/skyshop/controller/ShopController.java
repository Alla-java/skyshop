package org.skypro.skyshop.controller;

import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.SearchService;
import org.skypro.skyshop.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class ShopController {

    private final StorageService storageService;
    private final SearchService searchService;

    // Внедрение StorageService через конструктор
    @Autowired
    public ShopController(StorageService storageService, SearchService searchService) {
        this.storageService = storageService;
        this.searchService = searchService;
    }

    // Метод для получения всех продуктов
    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        // Возвращаем все продукты, доступные в StorageService
        return storageService.getProductStorage().values();
    }

    // Метод для получения всех статей
    @GetMapping("/articles")
    public Collection<Article> getAllArticles() {
        // Возвращаем все статьи, доступные в StorageService
        return storageService.getArticleStorage().values();
    }

    // Новый метод для поиска по строке
    @GetMapping("/search")
    public Collection<SearchResult> search(@RequestParam String pattern) {
        // Используем сервис поиска для получения результатов по строке pattern
        return searchService.search(pattern);
    }
}
