package com.example.feign.service;

import com.example.feign.client.PostsClient;
import com.example.feign.client.ProfileClient;
import com.example.feign.dto.PostDto;
import com.example.feign.dto.ProfileDto;
import com.example.feign.factory.ProfileClientFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclaredFeignClientService implements FeignClientService {


    @Autowired
    private PostsClient postsClient;

    @Autowired
    private ProfileClientFactory factory;

    public List<PostDto> getPosts() {
        return postsClient.getPosts();
    }

    @Override
    public PostDto getPost(String id) {
        return postsClient.getPost(id);
    }

    @Override
    public PostDto postPost(PostDto postDto) {
        return postsClient.postPosts(postDto);
    }

    @Override
    public ResponseEntity<String> deletePost(String id) {
        return postsClient.deletePosts(id);
    }

    @Override
    public PostDto putPost(PostDto postDto, String id) {
        return postsClient.putPosts(postDto, id);
    }

    @Override
    public ProfileDto getProfile() throws Exception {

        ProfileClient client =
                factory.createProfileClient("https://my-json-server.typicode.com/wildchild04/j4guanatos-springboot-feign/profile");
        return client.getProfile();
    }

}
