package top.hengshare.cloud.movie.feign;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import top.hengshare.cloud.movie.config.FeignDisableHystrixConfiguration;
import top.hengshare.cloud.movie.pojo.User;

@FeignClient(name = "user", configuration = FeignDisableHystrixConfiguration.class, fallbackFactory = UserFeignClient.FeignClientFallbackFactory.class)
public interface UserFeignClient {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    User findById(@PathVariable("id") Long id);

    /**
     * 获取出错异常原因
     */
    @Component
    class FeignClientFallbackFactory implements FallbackFactory<UserFeignClient> {
        private static final Logger LOGGER = LoggerFactory.getLogger(FeignClientFallbackFactory.class);

        @Override
        public UserFeignClient create(Throwable throwable) {
            return new UserFeignClient() {
                @Override
                public User findById(Long id) {
                    // 日志最好放在各个fallback方法中，而不要直接放在create方法中
                    // 否则在引用启动时，就会打印该日志
                    FeignClientFallbackFactory.LOGGER.info("fallback;reason was: ", throwable);
                    User user = new User();
                    user.setId(-1L);
                    user.setUsername("默认用户");
                    return user;
                }
            };
        }
    }
}
