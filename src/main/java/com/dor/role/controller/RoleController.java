package com.dor.role.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dor.role.constants.APIURLConstants;
import com.dor.role.dto.AbstractFetchRequestDTO;
import com.dor.role.dto.AbstractFetchResponseDTO;
import com.dor.role.dto.AbstractRequestDTO;
import com.dor.role.dto.AbstractResponseDTO;
import com.dor.role.dto.ApplicationListResponseDTO;
import com.dor.role.dto.RoleFunctionMapRequestDTO;
import com.dor.role.dto.UserRoleMapRequestDTO;
import com.dor.role.dto.UserRoleMapResponseDTO;
import com.dor.role.dto.UserRoleRequestDTO;
import com.dor.role.service.RoleService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping(APIURLConstants.API_VERSION)
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@PostMapping(APIURLConstants.SEND_OTP)
	public ResponseEntity<AbstractResponseDTO> sendOTP(@Validated @RequestBody AbstractRequestDTO requestDTO) throws Exception
	{
		AbstractResponseDTO responseDTO=new AbstractResponseDTO();
		responseDTO=roleService.sendOTP(requestDTO);
		if(responseDTO.getStatus()==true)
		{
			return ResponseEntity.ok(responseDTO);
		}
		else
			return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping(APIURLConstants.CREATE_ROLE)
	public ResponseEntity<AbstractResponseDTO> createRole(@Validated @RequestBody AbstractRequestDTO requestDTO) throws Exception
	{
		AbstractResponseDTO responseDTO=new AbstractResponseDTO();
		responseDTO=roleService.createRole(requestDTO);
		if(responseDTO.getStatus()==true)
		{
			return ResponseEntity.ok(responseDTO);
		}
		else
			return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping(APIURLConstants.CREATE_FUNCTION)
	public ResponseEntity<AbstractResponseDTO> createFunction(@Validated @RequestBody AbstractRequestDTO requestDTO) throws Exception
	{
		AbstractResponseDTO responseDTO=new AbstractResponseDTO();
		responseDTO=roleService.createFunction(requestDTO);
		if(responseDTO.getStatus()==true)
		{
			return ResponseEntity.ok(responseDTO);
		}
		else
			return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping(APIURLConstants.FETCH_APPLICATION_DATA)
	public ResponseEntity<ApplicationListResponseDTO> fetchApplicationData() throws Exception
	{
		ApplicationListResponseDTO responseDTO=new ApplicationListResponseDTO();
		responseDTO=roleService.fetchApplicationData();
		if(responseDTO.getStatus()==true)
		{
			return ResponseEntity.ok(responseDTO);
		}
		else
			return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping(APIURLConstants.FETCH_DATA)
	public ResponseEntity<AbstractFetchResponseDTO> fetchData(@Validated @RequestBody AbstractFetchRequestDTO requestDTO) throws Exception
	{
		AbstractFetchResponseDTO responseDTO=new AbstractFetchResponseDTO();
		responseDTO=roleService.fetchData(requestDTO);
		if(responseDTO.getStatus()==true)
		{
			return ResponseEntity.ok(responseDTO);
		}
		else
			return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping(APIURLConstants.MAP_ROLE_FUNCTION)
	public ResponseEntity<AbstractResponseDTO> mapRoleFunction(@Validated @RequestBody RoleFunctionMapRequestDTO requestDTO) throws Exception
	{
		AbstractResponseDTO responseDTO=new AbstractResponseDTO();
		responseDTO=roleService.mapRoleFunction(requestDTO);
		if(responseDTO.getStatus()==true)
		{
			return ResponseEntity.ok(responseDTO);
		}
		else
			return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
	}
	
	@PostMapping(APIURLConstants.MAP_USER_ROLE)
	public ResponseEntity<UserRoleMapResponseDTO> mapUserRole(@Validated @RequestBody UserRoleMapRequestDTO requestDTO) throws Exception
	{
		UserRoleMapResponseDTO responseDTO=new UserRoleMapResponseDTO();
		responseDTO=roleService.mapUserRole(requestDTO);
		if(responseDTO.getStatus()==true)
		{
			return ResponseEntity.ok(responseDTO);
		}
		else
			return new ResponseEntity<>(responseDTO, HttpStatus.NOT_ACCEPTABLE);
	}

}
