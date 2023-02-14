package com.dor.role.dto;

import java.util.List;

import com.dor.role.model.FunctionEntity;
import com.dor.role.model.RoleEntity;

public class AbstractFetchResponseDTO {
	
	public List<RoleEntity> roleData;
	public List<FunctionEntity> functionData;
	public String msg;
	public Boolean status;
	
	public List<RoleEntity> getRoleData() {
		return roleData;
	}
	public void setRoleData(List<RoleEntity> roleData) {
		this.roleData = roleData;
	}
	public List<FunctionEntity> getFunctionData() {
		return functionData;
	}
	public void setFunctionData(List<FunctionEntity> functionData) {
		this.functionData = functionData;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	
}
