package com.taxi.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.order.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
