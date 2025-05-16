package com.rest.repository;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rest.model.UserDtls;

@Repository
public interface UserDtlsRepository extends JpaRepository<UserDtls, Integer>  { 

	public UserDtls findByUsername(String username);
}
