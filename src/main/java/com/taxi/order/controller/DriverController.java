package com.taxi.order.controller;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Point;
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
import com.taxi.order.repository.DriverRepository;

@RestController
@RequestMapping("/taxi/")
public class DriverController {
	
	@Autowired
	private DriverRepository driverRepository;
	
	@GetMapping("driver")
	public List<Driver> getAllDriver(){
		return this.driverRepository.findAll();
	}
	
	@GetMapping("driver/{id}")
	public ResponseEntity<Driver> getDriverById(@PathVariable(value = "id") Long driverId) throws ResourceNotFoundException {
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));
		return ResponseEntity.ok().body(driver);
	}
	
	@GetMapping("driver/incomePerWeek/{id}")
	public List<Map<String, BigInteger>> getIncomePerWeek(@PathVariable(value = "id") Long driverId) {
		return this.driverRepository.calculateIncomePerWeek(driverId);
	}
	
	@PostMapping("driver")
	public ResponseEntity<Driver> createDriver(@RequestBody Driver driver) throws ResourceNotFoundException {
		this.driverRepository.save(driver);
		Driver drv = this.driverRepository.findById(driver.getId()).orElseThrow(() -> new ResourceNotFoundException("Driver detail cannot be saved"));
		return ResponseEntity.ok().body(drv);
	}
	
	@PutMapping("driver/{id}")
	public ResponseEntity<Driver> updateDriver(@PathVariable(value = "id") Long driverId, @Validated @RequestBody Driver driverDetails) throws ResourceNotFoundException {
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));
		driver.setNama(driverDetails.getNama());
		driver.setLat(driverDetails.getLat());
		driver.setLng(driverDetails.getLng());
		driver.setCreatedAt(driverDetails.getCreatedAt());
		return ResponseEntity.ok(this.driverRepository.save(driver));
	}
	
	@PutMapping("driver/nearest")
	public List<Driver> getAllDriverNearest(@Validated @RequestBody Point locDetails) throws ResourceNotFoundException {
		return this.driverRepository.findDriverNearestAndNotHaveOrder(locDetails.getY(), locDetails.getX());
	}
	
	@PutMapping("driver/updateLoc/{id}")
	public ResponseEntity<Driver> updateLatLonDriver(@PathVariable(value = "id") Long driverId, @Validated @RequestBody Point locDetails) throws ResourceNotFoundException {
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));
		driver.setLat(locDetails.getY());
		driver.setLng(locDetails.getX());
		return ResponseEntity.ok(this.driverRepository.save(driver));
	}
	
	@DeleteMapping("driver/{id}")
	public Map<String, Boolean> deleteDriver(@PathVariable(value = "id") Long driverId) throws ResourceNotFoundException {
		Driver driver = driverRepository.findById(driverId).orElseThrow(() -> new ResourceNotFoundException("Driver not found for this id :: " + driverId));
		this.driverRepository.delete(driver);
		
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		
		return response;
	}

}
