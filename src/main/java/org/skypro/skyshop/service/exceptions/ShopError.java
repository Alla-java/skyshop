package org.skypro.skyshop.service.exceptions;

public final class ShopError {

    private final String code;
    private final String message;

    // Конструктор для инициализации полей
    public ShopError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    // Геттер для поля code
    public String getCode() {
        return code;
    }

    // Геттер для поля message
    public String getMessage() {
        return message;
    }

    // Переопределяем метод toString() для удобного вывода ошибки
    @Override
    public String toString() {
        return "ShopError{code='" + code + "', message='" + message + "'}";
    }
}
