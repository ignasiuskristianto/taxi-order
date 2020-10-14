package com.taxi.order.controller;

import java.math.BigInteger;
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
import com.taxi.order.model.Driver;
import com.taxi.order.model.Order;
import com.taxi.order.model.User;
import com.taxi.order.repository.DriverRepository;
import com.taxi.order.repository.OrderRepository;
import com.taxi.order.repository.UserRepository;

@RestController
@RequestMapping("/taxi/")
public class OrderController {
	
	@Autowired
	private DriverRepository driverRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("order")
	public List<Order> getAllOrder(){
		return this.orderRepository.findAll();
	}
	
	@GetMapping("order/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
		return ResponseEntity.ok().body(order);
	}
	
	@PostMapping("order/{idUser}/{idDriver}")
	public ResponseEntity<Order> createOrder(@PathVariable(value = "idUser") Long userId, @PathVariable(value = "idDriver") Long driverId, @RequestBody Order order) throws ResourceNotFoundException {
		User user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not found for this id :: " + userId));
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));
		order.setDriver(driver);
		order.setUser(user);
		order.setFee(BigInteger.valueOf((long) (Math.sqrt((order.getLngDestPoint()-order.getLngStartPoint())*(order.getLngDestPoint()-order.getLngStartPoint()) + (order.getLatDestPoint()-order.getLatStartPoint())*(order.getLatDestPoint()-order.getLatStartPoint()))*1000)));
		order.setFlag('1');
		this.orderRepository.save(order);
		Order ord = this.orderRepository.findById(order.getId()).orElseThrow(() -> new ResourceNotFoundException("Order cannot be saved"));
		return ResponseEntity.ok().body(ord);
	}
	
	@PutMapping("order/{id}")
	public ResponseEntity<Order> updateOrder(@PathVariable(value = "id") Long orderId, @Validated @RequestBody Order orderDetails) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
		order.setDriver(orderDetails.getDriver());
		order.setUser(orderDetails.getUser());
		order.setOrderDate(orderDetails.getOrderDate());
		order.setLatStartPoint(orderDetails.getLatStartPoint());
		order.setLngStartPoint(orderDetails.getLngStartPoint());
		order.setLatDestPoint(orderDetails.getLatDestPoint());
		order.setLngDestPoint(orderDetails.getLngDestPoint());
		order.setFee(orderDetails.getFee());
		order.setFlag(orderDetails.getFlag());
		return ResponseEntity.ok(this.orderRepository.save(order));
	}
	
	@GetMapping("order/done/{id}")
	public Map<String, String> doneOrder(@PathVariable(value = "id") Long orderId) throws ResourceNotFoundException {
		Order order = orderRepository.findById(orderId).orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
		order.setFlag('0');
		this.orderRepository.save(order);
		
		Map<String, String> response = new HashMap<>();
		response.put("Order is done", "Fee : " + order.getFee());
		
		return response;
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
