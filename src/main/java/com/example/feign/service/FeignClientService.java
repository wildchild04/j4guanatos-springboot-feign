package com.example.feign.service;

import com.example.feign.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeignClientService {
    public List<PostDto> getPosts();
}
