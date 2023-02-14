package com.dor.role.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dor.role.model.APICallLogEntity;

public interface APICallLogRepository extends JpaRepository<APICallLogEntity, Integer> {

}
