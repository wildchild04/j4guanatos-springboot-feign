package com.example.feign.client;

import com.example.feign.dto.ProfileDto;
import feign.RequestLine;


public interface ProfileClient {

    @RequestLine("GET")
    ProfileDto getProfile();
}
