package com.dor.role.dto;

import java.util.List;

public class ApplicationListResponseDTO {
	
	public List<ApplicationListDTO> applicationList;
	public Boolean status;
	public String msg;
	
	public List<ApplicationListDTO> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(List<ApplicationListDTO> applicationList) {
		this.applicationList = applicationList;
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
