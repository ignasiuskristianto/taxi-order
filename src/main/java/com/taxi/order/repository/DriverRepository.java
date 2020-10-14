package com.taxi.order.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.taxi.order.model.Driver;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
	
	@Query(value = "select d.*, point(:lat,:lng) <-> point(d.lat,d.lng) as distance from taxi_driver d left join taxi_order o on d.id = o.driver_id where o.flag = '0' or o.id is null order by distance asc limit 5", nativeQuery=true)
	public List<Driver> findDriverNearestAndNotHaveOrder(@Param("lat") double lat, @Param("lng") double lng);
	
	@Query(value = "select date_trunc('week', o.order_date) as week, sum(o.fee) as income from taxi_order o where o.driver_id = :id group by week order by week desc", nativeQuery=true)
	public List<Map<String, BigInteger>> calculateIncomePerWeek(@Param("id") Long id);

}
