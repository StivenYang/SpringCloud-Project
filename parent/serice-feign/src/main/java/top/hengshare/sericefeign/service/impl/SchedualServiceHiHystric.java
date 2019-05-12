package top.hengshare.sericefeign.service.impl;

import org.springframework.stereotype.Component;
import top.hengshare.sericefeign.service.SchedualServiceHi;

/**
 * @author StivenYang
 * @program parent
 * @description Feign熔断器应用
 * @date 2019-05-12 11:40
 **/
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry, " + name;
    }
}
