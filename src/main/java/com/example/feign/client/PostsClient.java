package com.example.feign.client;

import com.example.feign.dto.PostDto;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "${example.fakerest:fakerest}", url = "${example.fakerest.api:https://my-json-server.typicode.com/wildchild04/j4guanatos-springboot-feign}")
public interface PostsClient {

    @RequestMapping(method = RequestMethod.GET, value = "/posts")
    List<PostDto> getPosts();
}
