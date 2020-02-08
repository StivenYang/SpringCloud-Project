package top.hengshare.cloud.user.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import top.hengshare.cloud.user.dao.UserRepository;
import top.hengshare.cloud.user.entity.User;

import java.util.Collection;

@RestController
@RefreshScope
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserRepository userRepository;

    @Value("${profile}")
    private String profile;

    @HystrixCommand
    @GetMapping("/{id}")
    public User findById(@PathVariable Long id){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails user = (UserDetails) principal;
            Collection<? extends GrantedAuthority> authorities =
                    user.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                //打印当前登录用户的信息
                UserController.LOGGER.info("当前用户是{}, 角色是{}", user.getUsername(), user.getAuthorities());
            }
        }else {
            // do other things
        }
        User findOne = this.userRepository.findOne(id);
        return findOne;
    }

    @GetMapping("/user/{id}")
    @HystrixCommand
    public User findOne(@PathVariable Long id){
        return userRepository.findOne(id);
    }

    @GetMapping("/profile")
    @HystrixCommand
    public String hello(){
        return this.profile;
    }
}
