package top.hengshare.sericefeign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import top.hengshare.sericefeign.service.SchedualServiceHi;

/**
 * @author StivenYang
 * @program parent
 * @description Feign接口调用
 * @date 2019-05-12 11:10
 **/
@RestController
public class FeignHello {

    @Autowired
    SchedualServiceHi schedualServiceHi;

    @GetMapping(value = "hi")
    public String sayHi(@RequestParam String name) {
        return schedualServiceHi.sayHiFromClientOne(name);
    }
}
