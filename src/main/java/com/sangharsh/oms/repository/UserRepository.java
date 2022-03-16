package com.sangharsh.oms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sangharsh.oms.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	User findByEmail(String email);

	boolean existsByEmail(String email);

}
