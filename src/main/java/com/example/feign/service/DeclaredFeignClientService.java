package com.example.feign.service;

import com.example.feign.client.PostsClient;
import com.example.feign.dto.PostsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclaredFeignClientService {


    @Autowired
    private PostsClient postsClient;

    public PostsDto getPosts() {
        return postsClient.getPosts();
    }

}
