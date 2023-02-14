package com.dor.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dor.role.model.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, String>{
	
}

