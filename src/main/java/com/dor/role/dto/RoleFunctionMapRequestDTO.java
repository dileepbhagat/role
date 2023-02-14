package com.dor.role.dto;

import java.util.List;

import com.dor.role.model.FunctionEntity;
import com.dor.role.model.RoleEntity;

public class RoleFunctionMapRequestDTO {
	
	public List<RoleDataRequestDTO> roleData;
	public List<FunctionDataRequestDTO> functionData;
	public Integer appId;
	public String creatorLoginId;
	
	public List<RoleDataRequestDTO> getRoleData() {
		return roleData;
	}
	public void setRoleData(List<RoleDataRequestDTO> roleData) {
		this.roleData = roleData;
	}
	public List<FunctionDataRequestDTO> getFunctionData() {
		return functionData;
	}
	public void setFunctionData(List<FunctionDataRequestDTO> functionData) {
		this.functionData = functionData;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getCreatorLoginId() {
		return creatorLoginId;
	}
	public void setCreatorLoginId(String creatorLoginId) {
		this.creatorLoginId = creatorLoginId;
	}
	
}
