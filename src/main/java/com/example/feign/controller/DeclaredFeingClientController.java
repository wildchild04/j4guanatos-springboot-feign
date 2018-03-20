package com.example.feign.controller;

import com.example.feign.dto.PostsDto;
import com.example.feign.service.DeclaredFeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class DeclaredFeingClientController {


    @Autowired
    DeclaredFeignClientService feignClientService;

    @RequestMapping(method = RequestMethod.GET, value = "/posts/")
    public PostsDto getPosts() {
        return feignClientService.getPosts();
    }
}
