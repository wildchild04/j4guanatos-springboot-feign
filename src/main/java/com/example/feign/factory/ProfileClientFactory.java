package com.example.feign.factory;

import com.example.feign.client.ProfileClient;
import feign.Client;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileClientFactory  {

    @Autowired
    private Client client;


    public ProfileClient createProfileClient(String url) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(ProfileClient.class, url);
    }
}
