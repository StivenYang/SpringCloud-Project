package top.hengshare.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.hengshare.blog.utils.ResponseEntityUtil;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class, args);
    }

    @GetMapping("hello")
    public ResponseEntityUtil hello(){
        return ResponseEntityUtil.success("123");
    }

    @GetMapping("hi")
    public ResponseEntityUtil hi(String name){
        return ResponseEntityUtil.success(name);
    }
}
