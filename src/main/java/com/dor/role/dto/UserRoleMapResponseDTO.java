package com.dor.role.dto;

import java.util.List;

public class UserRoleMapResponseDTO {
	
	public List<UserRoleMapDTO> userRoleMapList;
	public Boolean status;
	public String msg;
	
	public List<UserRoleMapDTO> getUserRoleMapList() {
		return userRoleMapList;
	}
	public void setUserRoleMapList(List<UserRoleMapDTO> userRoleMapList) {
		this.userRoleMapList = userRoleMapList;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

}
