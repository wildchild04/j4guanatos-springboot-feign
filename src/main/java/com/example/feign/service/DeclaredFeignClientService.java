package com.example.feign.service;

import com.example.feign.client.PostsClient;
import com.example.feign.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclaredFeignClientService implements FeignClientService{


    @Autowired
    private PostsClient postsClient;

    public List<PostDto> getPosts() {
        return postsClient.getPosts();
    }

}
