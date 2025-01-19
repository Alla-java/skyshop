package org.skypro.skyshop.model.search;

public final class SearchResult {

    private final String id;         // Идентификатор результата поиска
    private final String name;       // Имя результата поиска
    private final String contentType; // Тип контента результата поиска

    public SearchResult(String id, String name, String contentType) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("id не может быть пустым");
        }
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name не может быть пустым");
        }
        if (contentType == null || contentType.isEmpty()) {
            throw new IllegalArgumentException("contentType не может быть пустым");
        }

        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    // Статический метод для создания SearchResult из объекта Searchable
    public static SearchResult fromSearchable(Searchable searchable) {
        if (searchable == null) {
            throw new IllegalArgumentException("Searchable объект не может быть null");
        }

        // Используем данные из объекта Searchable для создания SearchResult
        return new SearchResult(
                searchable.getId().toString(),     // id как строка
                searchable.getName(),              // name
                searchable.getSearchTerm()         // contentType
        );
    }

    // Геттеры для всех полей
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContentType() {
        return contentType;
    }

    // Переопределение метода toString() для удобного отображения объекта
    @Override
    public String toString() {
        return "SearchResult{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", contentType='" + contentType + '\'' +
                '}';
    }
}
