package com.example.feign.controller;

import com.example.feign.dto.PostDto;
import com.example.feign.service.FeignClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeclaredFeingClientController {


    @Autowired
    FeignClientService feignClientService;

    @RequestMapping(method = RequestMethod.GET, value = "/posts/")
    public List<PostDto> getPosts() {
        return feignClientService.getPosts();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/posts/")
    public PostDto postPosts(@RequestBody PostDto postDto) {
        return feignClientService.postPost(postDto);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    public PostDto postPosts(@PathVariable String id) {
        return feignClientService.getPost(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    public ResponseEntity<String> deletePosts(@PathVariable String id) {
        return feignClientService.deletePost(id);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    public PostDto putPosts(@RequestBody PostDto postDto, @PathVariable String id) {
        return feignClientService.putPost(postDto, id);
    }


}

