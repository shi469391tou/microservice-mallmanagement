package com.itheima.controller;

import com.itheima.mapper.UserMapper;
import com.itheima.po.Order;
import com.itheima.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private RestTemplate restTemplate;
	@Autowired
	private UserMapper userMapper;
	@Value("${ORDERSERVICEURL}")
	private String ORDERSERVICEURL;

	@GetMapping(path="/findOrders/{username}")
	public List<Order> getOrderByUsername(@PathVariable("username")
												String username) {
		User user = this.userMapper.selectUser(username);
		//使用Ribbon后，可以使用http://order-service/而不用使用ip+端口
		ResponseEntity<List<Order>> rateResponse =
			      restTemplate.exchange(ORDERSERVICEURL
					+"/order/findOrders/"+user.getId(),
					HttpMethod.GET, null, 
					new ParameterizedTypeReference<List<Order>>(){});
		List<Order> orders = rateResponse.getBody();
		return orders;
	}
}