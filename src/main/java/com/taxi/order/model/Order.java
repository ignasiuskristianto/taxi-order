package com.taxi.order.model;

import java.awt.Point;
import java.math.BigInteger;
import java.util.Date;

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
	
	@Column(name = "order_date")
	private Date order_date;
	
	@Column(name = "start_point")
	private Point start_point;
	
	@Column(name = "dest_point")
	private Point dest_point;
	
	@Column(name = "fee")
	private BigInteger fee;
	
	@Column(name = "flag")
	private char flag;

	public Order() {
		super();
	}

	public Order(Driver driver, User user, Date order_date, Point start_point, Point dest_point,
			BigInteger fee, char flag) {
		super();
		this.driver = driver;
		this.user = user;
		this.order_date = order_date;
		this.start_point = start_point;
		this.dest_point = dest_point;
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

	public Date getOrder_date() {
		return order_date;
	}

	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}

	public Point getStart_point() {
		return start_point;
	}

	public void setStart_point(Point start_point) {
		this.start_point = start_point;
	}

	public Point getDest_point() {
		return dest_point;
	}

	public void setDest_point(Point dest_point) {
		this.dest_point = dest_point;
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
