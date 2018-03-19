package com.example.feign.factory;

import feign.codec.Decoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignServiceConfiguration {

    @Bean
    public ProfileClientFactory profileClientFactory() {
        return new ProfileClientFactory();
    }


}
