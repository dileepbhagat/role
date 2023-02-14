package com.dor.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dor.role.model.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer>{
	
	public RoleEntity findBySerialNo(Integer serialNo);
	public List<RoleEntity> findByAppId(Integer appId);
}
