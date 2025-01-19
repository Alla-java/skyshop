package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public final class Article implements Searchable {
    private final String title; // Название статьи
    private final String content; // Текст статьи
    private final UUID id; // Уникальный идентификатор статьи

    public Article(String title, String content) {

        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Название статьи не может быть пустым");
        }
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Текст статьи не может быть пустым");
        }

        this.title = title;
        this.content = content;
        this.id = UUID.randomUUID(); // Генерация уникального идентификатора
    }


    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override @JsonIgnore
    public String getSearchTerm() {
        return toString();
    }

    @Override
    public String getName() {
        return getTitle();
    }

    @Override
    public String getStringRepresentation() {
        return getTitle() + " — ARTICLE";
    }

    @Override
    public String toString() {
        return title + "\n" + content;
    }

}
