# Tutorial

## Intro
This is a little tutorial that will show you how to create REST API clients or
Integration API clients using Spring boot and Spring cloud Feign.

The branch `master` has the final version of the code.
To use it you will require:
- Java 8

To run the project just type `./gradlew runboot`.

The file [Post Example.postman_collection.json](https://github.com/wildchild04/j4guanatos-springboot-feign/blob/master/Post%20Example.postman_collection.json) can be imported to [Postman](https://www.getpostman.com/) and you can test the running service on `localhost:8080`


To start the tutorial you can checkout the branch `start`  

###Step1

This is the initial step. On this step we have the base files of a [Gradle](https://gradle.org/) project

We will:
1. Create the `Application.java`
2. Create a simple `HelloController`
3. Verify that we have a simple Springboot application running
4. Create the Feign client interface, data transfer objects, controllers and services
java objects

#####1. Create the Application.java

Create the file `src/main/java/Application.java`

```java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableFeignClients
@ComponentScan({"hello","com.example.feign"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
```
#####2. Create a simple HelloController

Create the file `src/main/java/hello/HelloController.java`

```java
package hello;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}
```

####3. Verify that we have a simple Springboot application running

At this point you can run the command `./gradlew bootrun` (`gradlew.bat` for Windows :)) or `gradle bootrun` if you have Gradle installed. After Spring boot finishes to start you can take a look at `localhost:8080` and you should see:

```
Greetings from Spring Boot!
```

####4. Create the Feign client interface, data transfer objects, controllers and services java objects

First we will create the Data Transfer Object (Dto) to represent a Post object

```json
{
  "id": 1,
  "title": "hello"
}
```

So we need to create the Java Class `src/main/java/com/example/feign/dto/PostDto.java`

```java
package com.example.feign.dto;

import java.util.Objects;

public class PostDto {

    private String id;
    private String title;

    public String getId() {
        return id;
    }

    public void setId(final String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final PostDto postDto = (PostDto) o;
        return Objects.equals(id, postDto.id)
                && Objects.equals(title, postDto.title);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title);
    }

    @Override
    public String toString() {
        return "PostDto{"
                + "id='" + id + '\''
                + ", title='" + title + '\''
                + '}';
    }
}

```

To create the API object that will fetch [Post](https://my-json-server.typicode.com/wildchild04/j4guanatos-springboot-feign/posts) objects from our REST service we will create the Java Interface `src/main/java/com/example/feign/client/PostsClient.java`. This will represent all our CRUD operations. For now, we will only add the `GET` method for Post.

```java
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

```

Now we need to create the Service classes, first we need a Service interface and then the implementation.
Create the Java Interface `src/main/java/com/example/feign/service/FeignClientService.java`.

```java
package com.example.feign.service;

import com.example.feign.dto.PostDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeignClientService {
    public List<PostDto> getPosts();
}

```

And then the Java class `src/main/java/com/example/feign/service/DeclaredFeignClientService.java`

```java
package com.example.feign.service;

import com.example.feign.client.PostsClient;
import com.example.feign.dto.PostDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeclaredFeignClientService implements FeignClientService{


    @Autowired
    private PostsClient postsClient;

    public List<PostDto> getPosts() {
        return postsClient.getPosts();
    }

}

```

And finally, we just need to create a RestController to consume the Post objects.

Create the Java class `src/main/java/com/example/feign/controller/DeclaredFeingClientController.java`

```java
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

```
We run the project with `./gradlew bootrun` and we check `localhost:8080/posts/` and you should expect:

```json
[{"id":"1","title":"hello"}]
```

at this point you can checkout the branch `step1`.

###Step2

On this step we will:

1. Finish all the CRUD operation definition
2. Update the Service Interface
3. Update the Service implementation
4. Test our changes


####1. Finish all the CRUD operation definition

First we need to add the rest of the Http methods.
so on the file `src/main/java/com/example/feign/client/PostsClient.java` add the code lines:

```java
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
```

####2. Update the Service Interface

On the file `src/main/java/com/example/feign/service/FeignClientService.java`
add the lines:
```java
public interface FeignClientService {
    public List<PostDto> getPosts();

    public PostDto getPost(String id);

    public PostDto postPost(PostDto postDto);

    public ResponseEntity<String> deletePost(String id);

    public PostDto putPost(PostDto postDto, String id);
}
```

####3. Update the Service implementation

finally on the file `src/main/java/com/example/feign/service/DeclaredFeignClientService.java`
add the lines:

```java
public class DeclaredFeignClientService implements FeignClientService {


    @Autowired
    private PostsClient postsClient;

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

}
```

####4. Test our changes

To test the CURD operation we can use the Postman file [Post Example.postman_collection.json](https://github.com/wildchild04/j4guanatos-springboot-feign/blob/master/Post%20Example.postman_collection.json) can be imported to [Postman](https://www.getpostman.com/) by importing it, or we can use `Curl`

#####Curl for GET

```bash
curl -X GET \
  http://localhost:8080/posts/1   
```

#####Curl for POST
```bash
curl -X POST \
  http://localhost:8080/posts/ \
  -H 'Content-Type: application/json' \
  -d '{
    "id": "2",
    "title": "ohhh hi!"
}'
```

#####Curl for PUT
```bash
curl -X PUT \
  http://localhost:8080/posts/1 \
  -H 'Content-Type: application/json' \
  -d '{
    "id": "1",
    "title": "helloooo"
}'
```

#####Curl for DELETE
```bash
curl -X DELETE \
  http://localhost:8080/posts/1 \
  -H 'Content-Type: application/json' \
```
at this point you can checkout the branch `step2`.

###Step3

At this point we are almost done. For this last step, we will cover the scenario when we don't know the URL of the REST service or the integration that we need, so creating a Feign client when Spring boot initialize all the bean objects won't work. To solve this, we need to create Feign clients dynamically.

On this step we will:

1. Create the Profile Data Transfer Object
2. Create a Factory for Profile FeignClients
3. Create a Profile client and will update the Service and also the controller
4. Test the changes

####1. Create the Profile Data Transfer Object

On this step we will create a Dto to represent the [Profile](https://my-json-server.typicode.com/wildchild04/j4guanatos-springboot-feign/profile) object.

Create the Java class `src/main/java/com/example/feign/dto/ProfileDto.java`

```java
package com.example.feign.dto;

import java.util.Objects;

public class ProfileDto {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final ProfileDto that = (ProfileDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "ProfileDto{"
                + "name='" + name + '\''
                + '}';
    }
}
```
####2. Create a Factory for Profile FeignClients

create the Java class `src/main/java/com/example/feign/factory/ProfileClientFactory.java`

```java
package com.example.feign.factory;

import com.example.feign.client.ProfileClient;
import feign.Client;
import feign.Feign;
import feign.gson.GsonDecoder;
import org.springframework.beans.factory.annotation.Autowired;

public class ProfileClientFactory  {

    public ProfileClient createProfileClient(String url) {
        return Feign.builder()
                .decoder(new GsonDecoder())
                .target(ProfileClient.class, url);
    }
}

```

To create the `ProfileClient` we use the `Feign Builder`. for now, since we are just implementing the `GET` method, we only need to provide the `Feign decoder` implementation, this time we will use `GsonDecoder`. For more information about the `Feign Builder` check [OpenFeign github](https://github.com/OpenFeign/feign)

Create a configuration class so we can use the dependency injection fanciness that Spring provides

create the class `src/main/java/com/example/feign/factory/FeignServiceConfiguration.java`

```java
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
```

####3. Create a Profile client and will update the Service and also the controller

Create the client interface `src/main/java/com/example/feign/client/ProfileClient.java`

```java
package com.example.feign.client;

import com.example.feign.dto.ProfileDto;
import feign.RequestLine;


public interface ProfileClient {

    @RequestLine("GET")
    ProfileDto getProfile();
}
```
Note: In this interface we use the `@RequestLine`. We are not creating the Feign client with Spring infrastructure, we are using `Feign.core`

Now in order to create the `ProfileClient` we will implement a very simple Factory.



Update the `src/main/java/com/example/feign/service/FeignClientService.java`

```java
@Service
public interface FeignClientService {
    List<PostDto> getPosts();

    PostDto getPost(String id);

    PostDto postPost(PostDto postDto);

    ResponseEntity<String> deletePost(String id);

    PostDto putPost(PostDto postDto, String id);

    ProfileDto getProfile() throws Exception;
}
```

Now add the factory instance and the new method implementation to the file `src/main/java/com/example/feign/service/DeclaredFeignClientService.java`


```java
@Autowired
private ProfileClientFactory factory;
```

```java
@Override
public ProfileDto getProfile() throws Exception {

    ProfileClient client =
            factory.createProfileClient("https://my-json-server.typicode.com/wildchild04/j4guanatos-springboot-feign/profile");
    return client.getProfile();
}
```

and finally update the controller file `src/main/java/com/example/feign/controller/DeclaredFeingClientController.java` with the method

```java
@RequestMapping(method = RequestMethod.GET, value = "/profile")
public ProfileDto getProfile() throws Exception {
    return feignClientService.getProfile();
}
```

####4. Test our changes

Using Curl:

#####Curl for GET

```bash
curl -X GET \
  http://localhost:8080/profile \
```

We should expect
```json
{
  "name": "wildchild04"
}
```
