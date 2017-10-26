package com.itheima.controller;
import com.itheima.mapper.OrderMapper;
import com.itheima.po.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private OrderMapper orderMapper;

	@GetMapping(path="/findOrders/{userid}")
	public List<Order> findOrder(@PathVariable("userid") Integer userid) {
		List<Order> orders=  this.orderMapper.selectOrder(userid);
		return  orders;
	}
}