package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable{
    //Метод для получения термина поиска (search term)
    String getSearchTerm();

    //Метод для получения имени объекта
    String getName();

    //Метод для получения типа контента
    default String getStringRepresentation() {
        return getName() + " — " + getClass().getSimpleName();
    }
    // Метод для получения уникального идентификатора объекта (UUID)
    UUID getId();
}