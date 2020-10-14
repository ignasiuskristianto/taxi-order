package com.taxi.order.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "taxi.order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "driver.id")
	private Driver driver;
	
	@ManyToOne
	@JoinColumn(name = "user.id")
	private User user;
	
	@Column(name = "order_date", insertable = false, updatable = false, columnDefinition="timestamp default current_timestamp")
	private LocalDateTime orderDate;
	
	@Column(name = "lat_start_point")
	private double latStartPoint;
	
	@Column(name = "lng_start_point")
	private double lngStartPoint;
	
	@Column(name = "lat_dest_point")
	private double latDestPoint;
	
	@Column(name = "lng_dest_point")
	private double lngDestPoint;
	
	@Column(name = "fee")
	private BigInteger fee;
	
	@Column(name = "flag")
	private char flag;

	public Order() {
		super();
	}

	public Order(double latStartPoint, double lngStartPoint, double latDestPoint, double lngDestPoint) {
		super();
		this.latStartPoint = latStartPoint;
		this.lngStartPoint = lngStartPoint;
		this.latDestPoint = latDestPoint;
		this.lngDestPoint = lngDestPoint;
	}

	public Order(Driver driver, User user, LocalDateTime orderDate, double latStartPoint, double lngStartPoint, double latDestPoint, double lngDestPoint,
			BigInteger fee, char flag) {
		super();
		this.driver = driver;
		this.user = user;
		this.orderDate = orderDate;
		this.latStartPoint = latStartPoint;
		this.lngStartPoint = lngStartPoint;
		this.latDestPoint = latDestPoint;
		this.lngDestPoint = lngDestPoint;
		this.fee = fee;
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDateTime orderDate) {
		this.orderDate = orderDate;
	}

	public double getLatStartPoint() {
		return latStartPoint;
	}

	public void setLatStartPoint(double latStartPoint) {
		this.latStartPoint = latStartPoint;
	}

	public double getLngStartPoint() {
		return lngStartPoint;
	}

	public void setLngStartPoint(double lngStartPoint) {
		this.lngStartPoint = lngStartPoint;
	}

	public double getLatDestPoint() {
		return latDestPoint;
	}

	public void setLatDestPoint(double latDestPoint) {
		this.latDestPoint = latDestPoint;
	}

	public double getLngDestPoint() {
		return lngDestPoint;
	}

	public void setLngDestPoint(double lngDestPoint) {
		this.lngDestPoint = lngDestPoint;
	}

	public BigInteger getFee() {
		return fee;
	}

	public void setFee(BigInteger fee) {
		this.fee = fee;
	}

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		this.flag = flag;
	}
	
}
