package top.hengshare.serviceribbon.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author StivenYang
 * @program parent
 * @description Ribbon服务间调用
 * @date 2019-05-10 15:47
 **/
@Service
public class HelloService {

    @Autowired
    RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "hiError")
    public String hiService(String name) {
        return restTemplate.getForObject("http://SERVICE-BLOG/hi?name=" + name, String.class);
    }

    public String hiError(String name) {
        return "hi," + name + "出错了";
    }
}
