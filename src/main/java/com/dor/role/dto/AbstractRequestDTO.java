package com.dor.role.dto;

public class AbstractRequestDTO {
	
	public String roleName;
	public String roleShortName;
	public Integer appId;
	public String creatorLoginId;
	public String roleDesc;
	public String otp;
	public String functionName;
	public String functionShortCode;
	public String functionDesc;
	
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleShortName() {
		return roleShortName;
	}
	public void setRoleShortName(String roleShortName) {
		this.roleShortName = roleShortName;
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
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getOtp() {
		return otp;
	}
	public void setOtp(String otp) {
		this.otp = otp;
	}
	public String getFunctionName() {
		return functionName;
	}
	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}
	public String getFunctionShortCode() {
		return functionShortCode;
	}
	public void setFunctionShortCode(String functionShortCode) {
		this.functionShortCode = functionShortCode;
	}
	public String getFunctionDesc() {
		return functionDesc;
	}
	public void setFunctionDesc(String functionDesc) {
		this.functionDesc = functionDesc;
	}

}
