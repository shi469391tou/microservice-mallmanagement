package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient  //Eureka服务发现客户端配置
@SpringBootApplication
public class UserServiceApplication {
    @Bean
    @LoadBalanced //该注解可以使用ribbon的客户端负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    };

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }
}