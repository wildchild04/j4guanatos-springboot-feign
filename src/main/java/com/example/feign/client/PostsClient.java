package com.example.feign.client;

import com.example.feign.dto.PostDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "${example.fakerest:fakerest}", url = "${example.fakerest.api:https://my-json-server.typicode.com/wildchild04/j4guanatos-springboot-feign}")
public interface PostsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<PostDto> getPosts();

    @RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
    PostDto getPost(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.POST, value = "/posts}")
    PostDto postPosts(PostDto postDto);

    @RequestMapping(method = RequestMethod.DELETE, value = "/posts/{id}")
    ResponseEntity<String> deletePosts(@PathVariable("id") String id);

    @RequestMapping(method = RequestMethod.PUT, value = "/posts/{id}")
    PostDto putPosts(PostDto postDto, @PathVariable("id") String id);
}
