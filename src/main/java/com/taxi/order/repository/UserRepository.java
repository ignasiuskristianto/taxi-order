package com.taxi.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.taxi.order.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
