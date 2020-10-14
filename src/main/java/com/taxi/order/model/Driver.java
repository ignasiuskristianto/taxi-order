package com.taxi.order.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "taxi.driver")
public class Driver {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nama")
	private String nama;
	
	@Column(name = "lat")
	private double lat;
	
	@Column(name = "lng")
	private double lng;
	
	@Column(name = "created_at", insertable = false, updatable = false, columnDefinition="timestamp default current_timestamp")
	private LocalDateTime createdAt;

	public Driver() {
		super();
	}

	public Driver(String nama, double lat, double lng) {
		super();
		this.nama = nama;
		this.lat = lat;
		this.lng = lng;
	}
	
	public Driver(String nama, double lat, double lng, LocalDateTime createdAt) {
		super();
		this.nama = nama;
		this.lat = lat;
		this.lng = lng;
		this.createdAt = createdAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createAt) {
		this.createdAt = createAt;
	}

}
