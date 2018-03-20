package com.example.feign.service;

import com.example.feign.dto.PostDto;
import com.example.feign.dto.ProfileDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeignClientService {
    List<PostDto> getPosts();

    PostDto getPost(String id);

    PostDto postPost(PostDto postDto);

    ResponseEntity<String> deletePost(String id);

    PostDto putPost(PostDto postDto, String id);

    ProfileDto getProfile() throws Exception;
}
