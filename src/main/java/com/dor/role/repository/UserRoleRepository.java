package com.dor.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dor.role.model.UserRoleEntity;

public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
	
}
