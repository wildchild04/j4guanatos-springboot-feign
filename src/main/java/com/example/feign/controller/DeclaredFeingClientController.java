package com.example.feign.controller;

import com.example.feign.dto.PostDto;
import com.example.feign.service.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeclaredFeingClientController {


    @Autowired
    FeignClientService feignClientService;

    @RequestMapping(method = RequestMethod.GET, value = "/posts/")
    public List<PostDto> getPosts() {
        return feignClientService.getPosts();
    }
}
