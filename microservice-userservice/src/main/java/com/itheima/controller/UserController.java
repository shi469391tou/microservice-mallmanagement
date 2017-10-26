package com.itheima.controller;

import com.itheima.mapper.UserMapper;
import com.itheima.po.Order;
import com.itheima.po.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	private static final Logger LOG = 
			LoggerFactory.getLogger(UserController.class);
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserMapper userMapper;
	@Value("${ORDERSERVICEURL}")
	private String ORDERSERVICEURL;

	@GetMapping(path="/findOrders/{username}")
	@HystrixCommand(fallbackMethod = "getUserByNamefallback") //断路器
	public List<Order> getOrderByUsername(@PathVariable("username")
												String username) {
		ResponseEntity<List<Order>> rateResponse = null;
		try {
			User user = this.userMapper.selectUser(username);
			//使用ribbon后，可以使用http://order-service/而不用使用ip+端口
			rateResponse = restTemplate.exchange(ORDERSERVICEURL
					+"/order/findOrders/"+user.getId(),
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<List<Order>>(){});
		} catch (RestClientException e) {
			LOG.warn("getOrderByUsername fail:"+e.getMessage());
			e.printStackTrace();
		}
		List<Order> orders = rateResponse.getBody();
		return orders;
	}
	//针对上面断路器发现的问题编写fallback方法（参数和返回值要一样）
	public List<Order> getUserByNamefallback(String name) {
		List<Order> orders =new ArrayList<>();
		return orders;
	}
}