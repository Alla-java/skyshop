package org.skypro.skyshop.serviceTest;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.SearchService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collection;
import java.util.UUID;

import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.article.Article;

public class SearchServiceTests {

    // Создаем мок StorageService
    @Mock
    private StorageService storageService;

    // Внедряем мок в класс SearchService
    @InjectMocks
    private SearchService searchService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Инициализируем моки
    }

    //Тест на поиск, когда в хранилище нет объектов
    @Test
    public void testSearch_noResults() {
        // Настроим mock, чтобы метод getAllSearchables возвращал пустую коллекцию
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList());

        // Выполняем поиск
        Collection<SearchResult> results = searchService.search("яблоко");

        // Проверяем, что результатов нет
        assertTrue(results.isEmpty(), "В хранилище не таких объектов");
    }

    //Тест на поиск, когда в хранилище есть объекты, но они не соответствуют строке поиска
    @Test
    public void testSearch_noMatchingResults() {
        // Подготовим объекты для хранилища
        UUID productId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getName()).thenReturn("Огурцы");

        // Настроим mock, чтобы метод getAllSearchables возвращал коллекцию с продуктом
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(product));

        // Выполняем поиск
        Collection<SearchResult> results = searchService.search("яблоко");

        // Проверяем, что результатов поиска нет, так как нет совпадений
        assertTrue(results.isEmpty(), "Результаты поиска не соответствуют строке поиска");
    }

    //Тест на поиск, когда в хранилище есть объекты и они соответствуют строке поиска
    @Test
    public void testSearch_matchingResults() {
        // Подготовим объекты для хранилища
        UUID productId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(productId);
        when(product.getName()).thenReturn("Огурцы");

        Article article = mock(Article.class);
        when(article.getId()).thenReturn(UUID.randomUUID());
        when(article.getName()).thenReturn("Рыбы Черного моря");

        // Настроим mock, чтобы метод getAllSearchables возвращал оба объекта
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(product, article));

        // Выполняем поиск
        Collection<SearchResult> results = searchService.search("рыбы");

        // Проверяем, что один результат был найден
        assertEquals(1, results.size(), "Должен быть найден один результат");

        // Проверяем, что найден результат с нужным названием
        SearchResult result = results.iterator().next();
        assertEquals("Рыбы Черного моря", result.getName(), "Найден правильный результат");
    }

    //Тест на поиск, когда в хранилище есть объекты и они соответствуют строке поиска без учета регистра введенных символов
    @Test
    public void testSearch_caseInsensitive() {
        // Подготовим объекты для хранилища
        UUID productId = UUID.randomUUID();
        Product product = mock(Product.class);
        when(product.getId()).thenReturn(productId);
        when(product.getName()).thenReturn("Огурцы");

        // Настроим mock, чтобы метод getAllSearchables возвращал коллекцию с продуктом
        when(storageService.getAllSearchables()).thenReturn(Arrays.asList(product));

        // Выполняем поиск с разным регистром
        Collection<SearchResult> results1 = searchService.search("огурцы");
        Collection<SearchResult> results2 = searchService.search("ОГУРЦЫ");

        // Проверяем, что оба поиска дали одинаковые результаты
        assertEquals(1, results1.size(), "Должен быть найден один результат");
        assertEquals(1, results2.size(), "Должен быть найден один результат");
    }
}
