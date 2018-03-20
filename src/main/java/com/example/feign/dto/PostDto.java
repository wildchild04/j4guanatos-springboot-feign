package com.example.feign.dto;

import java.util.Objects;

public class PostDto {

    private String id;
    private String tittle;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(final String tittle) {
        this.tittle = tittle;
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
                && Objects.equals(tittle, postDto.tittle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, tittle);
    }

    @Override
    public String toString() {
        return "PostDto{"
                + "id='" + id + '\''
                + ", tittle='" + tittle + '\''
                + '}';
    }
}
