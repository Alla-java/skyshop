package org.skypro.skyshop.service;

import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SearchService {

    // Зависимость от StorageService
    private final StorageService storageService;

    // Конструктор с внедрением зависимости через конструктор
    @Autowired
    public SearchService(StorageService storageService) {
        this.storageService = storageService;
    }

    // Метод для поиска всех объектов Searchable по имени
    public Collection<SearchResult> search(String searchTerm) {
        // Получаем все объекты типа Searchable из хранилища
        return storageService.getAllSearchables().stream()
                // Фильтруем объекты, если их название содержит строку поиска (без учета регистра)
                .filter(searchable -> searchable.getName().toLowerCase().contains(searchTerm.toLowerCase()))
                // Преобразуем каждый объект Searchable в SearchResult
                .map(searchable -> new SearchResult(
                        searchable.getId().toString(),
                        searchable.getName(),
                        searchable.getSearchTerm()
                ))
                // Собираем результаты в коллекцию
                .collect(Collectors.toList());
    }


}
