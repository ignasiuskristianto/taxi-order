package com.taxi.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taxi.order.exception.ResourceNotFoundException;
import com.taxi.order.model.Order;
import com.taxi.order.repository.OrderRepository;

@RestController
@RequestMapping("/taxi/")
public class OrderController {
	
	@Autowired
	private OrderRepository orderRepository;
	
	@GetMapping("order")
	public List<Order> getAllOrder(){
		return this.orderRepository.findAll();
	}
	
	@GetMapping("order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping("order")
	public Order createOrder(@RequestBody Order order) {
		return this.orderRepository.save(order);		
	}
	
	@PutMapping("order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Long orderId, @Validated @RequestBody Order orderDetails) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
		order.setDriver(orderDetails.getDriver());
		order.setUser(orderDetails.getUser());
		order.setOrder_date(orderDetails.getOrder_date());
		order.setStart_point(orderDetails.getStart_point());
		order.setDest_point(orderDetails.getDest_point());
		order.setFee(orderDetails.getFee());
		order.setFlag(orderDetails.getFlag());
		return ResponseEntity.ok(this.orderRepository.save(order));
	}
	
	@DeleteMapping("order/{id}")
	public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
		this.orderRepository.delete(order);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}

}
