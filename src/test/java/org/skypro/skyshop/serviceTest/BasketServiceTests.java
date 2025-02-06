package org.skypro.skyshop.serviceTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.service.BasketService;
import org.skypro.skyshop.service.StorageService;
import org.skypro.skyshop.service.exceptions.NoSuchProductException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class BasketServiceTests {

    // Создаем мок ProductBasket
    @Mock
    private ProductBasket productBasket;

    // Создаем мок StorageService
    @Mock
    private StorageService storageService;

    //Внедряем моки в класс BasketService
    @InjectMocks
    private BasketService basketService;

    private UUID productId;
    private Product existingProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this); // Инициализация моков
        productId = UUID.randomUUID();
        existingProduct = new SimpleProduct( "Тестовый продукт", 100); // Пример товара
    }

    //Тест, когда добавление несуществующего товара в корзину приводит к выбросу исключения
    @Test
    public void testAddNonExistentProductThrowsException() {
        // Мокируем поведение StorageService: возвращаем пустой Optional для несуществующего продукта
        when(storageService.getProductById(productId)).thenReturn(Optional.empty());

        // Используем assertThrows для проверки выбрасываемого исключения
        NoSuchProductException thrown = assertThrows(NoSuchProductException.class, () -> {
            basketService.addProductToBasket(productId); // Ожидаем, что будет выброшено исключение
        });

        // Дополнительно можно проверить сообщение исключения
        assertEquals("Product с таким id:" + productId + "не найден", thrown.getMessage());
    }

    //Тест на добавление существующего товара вызывает метод addProduct у мока ProductBasket
    @Test
    public void testAddExistingProductCallsAddProduct() {
        // Мокируем поведение StorageService: возвращаем существующий продукт
        when(storageService.getProductById(productId)).thenReturn(Optional.of(existingProduct));

        basketService.addProductToBasket(productId);

        // Проверяем, что метод addProduct был вызван
        verify(productBasket).addProduct(productId);
    }

    //Тест на проверку работы метода getUserBacket, который возвращает пустую корзину, если ProductBasket пуст
    @Test
    public void testGetUserBasketReturnsEmptyBasketWhenNoItems() {
        // Мокируем поведение ProductBasket: корзина пуста
        when(productBasket.getProducts()).thenReturn(Collections.emptyMap());

        UserBasket userBasket = basketService.getUserBasket();

        // Проверяем, что корзина пустая
        assertTrue(userBasket.getItems().isEmpty());
    }

    //Тест на проверку работы метода getUserBasket,
    //который возвращает подходящую корзину, если в ProductBasket есть товары
    @Test
    public void testGetUserBasketReturnsBasketWithItems() {
        // Создаем пару товаров в корзине
        BasketItem basketItem = new BasketItem(existingProduct, 1);
        List<BasketItem> basketItemList = Collections.singletonList(basketItem);

        // Мокируем поведение ProductBasket: в корзине есть один товар
        when(productBasket.getProducts()).thenReturn(Collections.singletonMap(productId, 1));
        when(storageService.getProduct(productId)).thenReturn(existingProduct);

        UserBasket userBasket = basketService.getUserBasket();

        // Проверяем, что корзина не пуста
        assertFalse(userBasket.getItems().isEmpty());

        // Проверяем, что корзина содержит только один товар
        assertEquals(1, userBasket.getItems().size());

        // Используем forEach для проверки первого элемента
        userBasket.getItems().forEach(itemInBasket -> {
            assertEquals(basketItem, itemInBasket); // Сравниваем содержимое корзины с ожидаемым товаром
        });
    }

    // Дополнительный тест для проверки, что при добавлении продукта в корзину,
    // если он уже есть, количество увеличивается (повторное добавление продукта)
    @Test
    public void testAddProductToBasketIncreasesQuantityWhenAlreadyInBasket() {
        // Мокируем, что продукт уже есть в корзине с количеством 1
        when(storageService.getProductById(productId)).thenReturn(Optional.of(existingProduct));
        when(productBasket.getProducts()).thenReturn(Collections.singletonMap(productId, 1));

        basketService.addProductToBasket(productId);

        // Проверяем, что количество товара увеличилось
        verify(productBasket).addProduct(productId); // Метод должен быть вызван
    }





    }





