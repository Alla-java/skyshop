package org.skypro.skyshop.controller;

import org.skypro.skyshop.service.exceptions.NoSuchProductException;
import org.skypro.skyshop.service.exceptions.ShopError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ShopControllerAdvice {
    @ExceptionHandler(NoSuchProductException.class)
    public ResponseEntity<ShopError> handleNoSuchProductException(NoSuchProductException ex) {
        // Создаем объект ShopError с кодом ошибки и сообщением
        ShopError shopError = new ShopError("404", ex.getMessage());

        // Возвращаем ResponseEntity с ошибкой и статусом 404
        return new ResponseEntity<>(shopError, HttpStatus.NOT_FOUND);
    }
}
