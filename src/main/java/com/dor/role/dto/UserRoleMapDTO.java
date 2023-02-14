package com.dor.role.dto;

public class UserRoleMapDTO {
	
	public Integer appId;
	public String loginId;
	public Integer roleId;
	public Integer functionId;
	public String functionURL;
	
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
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getFunctionId() {
		return functionId;
	}
	public void setFunctionId(Integer functionId) {
		this.functionId = functionId;
	}
	public String getFunctionURL() {
		return functionURL;
	}
	public void setFunctionURL(String functionURL) {
		this.functionURL = functionURL;
	}

}
