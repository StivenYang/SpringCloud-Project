package top.hengshare.cloud.movie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.hengshare.cloud.movie.feign.CustomFeignClient;
import top.hengshare.cloud.movie.pojo.User;

@Import(FeignClientsConfiguration.class)
@RestController
public class CustomMovieController {

    private CustomFeignClient userCustomFeignClient;

    private CustomFeignClient adminCustomFeignClient;

    @Autowired
    public CustomMovieController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
        this.userCustomFeignClient = Feign.builder().client(client).encoder(encoder)
                .decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
                .target(CustomFeignClient.class, "http://user/");
        this.adminCustomFeignClient = Feign.builder().client(client).encoder(encoder)
                .decoder(decoder).contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
                .target(CustomFeignClient.class, "http://user/");
    }

    @GetMapping("/user-user/{id}")
    @HystrixCommand
    public User findByIdUser(@PathVariable Long id) {
        return this.userCustomFeignClient.findById(id);
    }

    @GetMapping("/user-admin/{id}")
    @HystrixCommand
    public User findByIdAdmin(@PathVariable Long id) {
        return this.adminCustomFeignClient.findById(id);
    }
}
