package com.dor.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dor.role.model.FunctionEntity;

public interface FunctionRepository extends JpaRepository<FunctionEntity, Integer>{
	
	public FunctionEntity findBySerialNo(Integer serialNo);
	public List<FunctionEntity> findByAppId(Integer appId);

}
