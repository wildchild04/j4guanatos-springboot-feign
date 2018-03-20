package com.example.feign.service;

import com.example.feign.dto.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeignClientService {
    public List<PostDto> getPosts();

    public PostDto getPost(String id);

    public PostDto postPost(PostDto postDto);

    public ResponseEntity<String> deletePost(String id);

    public PostDto putPost(PostDto postDto, String id);
}
