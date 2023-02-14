package com.dor.role.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dor.role.model.ApplicationEntity;

public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Integer>{
	
	public List<ApplicationEntity> findByEnabled(Boolean enabled);

}

