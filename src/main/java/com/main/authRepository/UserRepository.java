package com.main.authRepository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.main.authEntities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String name);
	
}
