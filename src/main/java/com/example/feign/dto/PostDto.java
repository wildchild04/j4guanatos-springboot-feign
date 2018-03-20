package com.example.feign.dto;

import java.util.Objects;

public class PostDto {

    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id)
                && Objects.equals(title, postDto.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "PostDto{"
                + "id='" + id + '\''
                + ", title='" + title + '\''
                + '}';
    }
}
