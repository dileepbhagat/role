package com.dor.role.service;

import com.dor.role.dto.AbstractFetchRequestDTO;
import com.dor.role.dto.AbstractFetchResponseDTO;
import com.dor.role.dto.AbstractRequestDTO;
import com.dor.role.dto.AbstractResponseDTO;
import com.dor.role.dto.ApplicationListResponseDTO;
import com.dor.role.dto.RoleFunctionMapRequestDTO;
import com.dor.role.dto.UserRoleMapRequestDTO;
import com.dor.role.dto.UserRoleMapResponseDTO;
import com.dor.role.dto.UserRoleRequestDTO;

public interface RoleService {
	
	public AbstractResponseDTO createRole(AbstractRequestDTO requestDTO) throws Exception;
	public AbstractResponseDTO sendOTP(AbstractRequestDTO requestDTO) throws Exception;
	public AbstractResponseDTO createFunction(AbstractRequestDTO requestDTO) throws Exception;
	public AbstractFetchResponseDTO fetchData(AbstractFetchRequestDTO requestDTO) throws Exception;
	public ApplicationListResponseDTO fetchApplicationData() throws Exception;
	public AbstractResponseDTO mapRoleFunction(RoleFunctionMapRequestDTO requestDTO) throws Exception;
	public UserRoleMapResponseDTO mapUserRole(UserRoleMapRequestDTO requestDTO) throws Exception;
}
