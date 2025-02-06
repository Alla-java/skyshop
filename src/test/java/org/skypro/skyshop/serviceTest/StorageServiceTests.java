package org.skypro.skyshop.serviceTest;

import org.junit.jupiter.api.Test;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.StorageService;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

import org.skypro.skyshop.service.exceptions.NoSuchProductException;

import java.util.Map;
import java.util.UUID;

public class StorageServiceTests {
    private StorageService storageService;

    @BeforeEach
    public void setUp() {
        storageService = new StorageService(); // Инициализируем сервис для тестов
    }

    // Тест на поиск в случае отсутствия объектов в StorageService
    @Test
    public void testSearchWhenStorageIsEmpty() {
        // Прокачиваем StorageService для имитации пустых хранилищ
        StorageService mockStorageService = mock(StorageService.class);
        when(mockStorageService.getProduct(UUID.randomUUID())).thenReturn(null);
        when(mockStorageService.getArticle(UUID.randomUUID())).thenReturn(null);

        // Проверка поиска продуктов
        assertThrows(NoSuchProductException.class, () -> mockStorageService.getProduct(UUID.randomUUID()));

        // Проверка поиска статей (к примеру, возвращаем null, если нет подходящей статьи)
        assertNull(mockStorageService.getArticle(UUID.randomUUID()));
    }

    // Тест на поиск в случае, если объекты в StorageService есть, но нет подходящего
    @Test
    public void testSearchWhenNoMatchingObject() {
        // Инициализируем пустые хранилища
        Map<UUID, Product> productStorage = storageService.getProductStorage();
        Map<UUID, Article> articleStorage = storageService.getArticleStorage();

        // Создаем UUID, который точно не существует в хранилище
        UUID nonexistentProductId = UUID.randomUUID();
        UUID nonexistentArticleId = UUID.randomUUID();

        // Проверка поиска продукта
        assertThrows(NoSuchProductException.class, () -> storageService.getProduct(nonexistentProductId));

        // Проверка поиска статьи (мы уверены, что ID нет в хранилище)
        assertNull(storageService.getArticle(nonexistentArticleId));
    }

    // Тест на поиск, когда есть подходящий объект в StorageService
    @Test
    public void testSearchWhenObjectExists() {
        // Инициализируем продукт для поиска
        Product product = new SimpleProduct("Помидоры на ветке", 229);
        UUID productId = product.getId();

        // Добавляем объект в хранилище
        storageService.getProductStorage().put(productId, product);

        // Проверка поиска продукта
        Product foundProduct = storageService.getProduct(productId);
        assertNotNull(foundProduct);
        assertEquals(productId, foundProduct.getId());
    }
}
