package top.hengshare.serviceribbon.service;

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


}
