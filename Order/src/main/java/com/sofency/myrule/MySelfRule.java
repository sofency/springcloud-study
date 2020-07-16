package com.sofency.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sofency
 * @date 2020/7/16 13:05
 * @package IntelliJ IDEA
 * @description
 */
@Configuration
public class MySelfRule {
    @Bean
    public IRule createMyRule(){
        return new RandomRule();//定义为随机的
    }
}
