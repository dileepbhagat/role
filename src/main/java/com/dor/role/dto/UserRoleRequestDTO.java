package com.dor.role.dto;

public class UserRoleRequestDTO {
	
	public String loginId;
	public Integer roleNo;
	public String creatorLoginId;
	
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
	public Integer getRoleNo() {
		return roleNo;
	}
	public void setRoleNo(Integer roleNo) {
		this.roleNo = roleNo;
	}
	public String getCreatorLoginId() {
		return creatorLoginId;
	}
	public void setCreatorLoginId(String creatorLoginId) {
		this.creatorLoginId = creatorLoginId;
	}
	
}
