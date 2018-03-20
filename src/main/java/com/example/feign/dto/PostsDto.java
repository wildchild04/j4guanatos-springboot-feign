package com.example.feign.dto;

import java.util.List;
import java.util.Objects;

public class PostsDto {

    private List<PostsDto> posts;

    public List<PostsDto> getPosts() {
        return posts;
    }

    public void setPosts(final List<PostsDto> posts) {
        this.posts = posts;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PostsDto postsDto = (PostsDto) o;
        return Objects.equals(posts, postsDto.posts);
    }

    @Override
    public int hashCode() {

        return Objects.hash(posts);
    }
}
