package top.hengshare.sericefeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import top.hengshare.sericefeign.service.impl.SchedualServiceHiHystric;

/**
 * @author StivenYang
 * @program parent
 * @description Feign接口
 * @date 2019-05-12 11:11
 **/
@FeignClient(value = "SERVICE-BLOG", fallback = SchedualServiceHiHystric.class)
public interface SchedualServiceHi {

    @RequestMapping(value = "hi", method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
