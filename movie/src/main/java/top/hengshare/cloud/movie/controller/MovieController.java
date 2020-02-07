package top.hengshare.cloud.movie.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.exception.HystrixBadRequestException;
import com.netflix.hystrix.exception.HystrixTimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import top.hengshare.cloud.movie.feign.UserFeignClient;
import top.hengshare.cloud.movie.pojo.User;

@RequestMapping
@RestController
public class MovieController {
    private static final Logger LOGGER = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private UserFeignClient userFeignClient;

    @HystrixCommand(fallbackMethod = "findByIdFallback", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                    value = "5000"),
            @HystrixProperty(name = "metrics.rollingStats.timeInMilliseconds", value = "10000")
    }, threadPoolProperties = {
            @HystrixProperty(name = "coreSize", value = "1"),
            @HystrixProperty(name = "maxQueueSize", value = "10")
    })
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id) {
        return this.restTemplate.getForObject("http://user/" + id, User.class);
    }

    @GetMapping("/log-user-instance")
    @HystrixCommand
    public void logUserInstance() {
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("user");
        //打印当前选择的是哪个节点
        MovieController.LOGGER.info("{}:{}:{}", serviceInstance.getServiceId(),
                serviceInstance.getHost(), serviceInstance.getPort());
    }

    @GetMapping("/user1/{id}")
    @HystrixCommand(fallbackMethod = "findByIdFallback", ignoreExceptions = {
            IllegalArgumentException.class, HystrixBadRequestException.class, HystrixTimeoutException.class
    })
    public User findById1(@PathVariable Long id) {
        return userFeignClient.findById(id);
    }

    public User findByIdFallback(Long id, Throwable throwable) {
        LOGGER.error("进入回退方法，异常：" + throwable);

        User user = new User();
        user.setId(-1L);
        user.setName("默认用户");
        return user;
    }
}
