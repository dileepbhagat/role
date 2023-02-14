package com.dor.role.dto;

public class UserRoleMapRequestDTO {
	
	public Integer roleSerialNo;
	public Integer appId;
	public String loginId;

	public Integer getRoleSerialNo() {
		return roleSerialNo;
	}

	public void setRoleSerialNo(Integer roleSerialNo) {
		this.roleSerialNo = roleSerialNo;
	}

	public Integer getAppId() {
		return appId;
	}

	public void setAppId(Integer appId) {
		this.appId = appId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

}
