package com.dor.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dor.role.model.RoleFunctionEntity;

public interface RoleFunctionRepository extends JpaRepository<RoleFunctionEntity, Integer>{
	
	public RoleFunctionEntity findBySerialNo(Integer serialNo);
	public List<RoleFunctionEntity> findByRoleSerialNo(Integer roleSerialNo);
}
