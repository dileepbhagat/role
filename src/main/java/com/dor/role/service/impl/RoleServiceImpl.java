package com.dor.role.service.impl;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dor.role.mapping.ObjectToJsonMapper;
import com.dor.role.model.APICallLogEntity;
import com.dor.role.model.ApplicationEntity;
import com.dor.role.model.FunctionEntity;
import com.dor.role.model.LoginEntity;
import com.dor.role.model.RoleEntity;
import com.dor.role.model.RoleFunctionEntity;
import com.dor.role.model.UserRoleEntity;
import com.dor.role.constants.APIConstants;
import com.dor.role.dto.AbstractFetchRequestDTO;
import com.dor.role.dto.AbstractFetchResponseDTO;
import com.dor.role.dto.AbstractRequestDTO;
import com.dor.role.dto.AbstractResponseDTO;
import com.dor.role.dto.ApplicationListDTO;
import com.dor.role.dto.ApplicationListResponseDTO;
import com.dor.role.dto.FunctionDataRequestDTO;
import com.dor.role.dto.RoleDataRequestDTO;
import com.dor.role.dto.RoleFunctionMapRequestDTO;
import com.dor.role.dto.UserRoleMapDTO;
import com.dor.role.dto.UserRoleMapRequestDTO;
import com.dor.role.dto.UserRoleMapResponseDTO;
import com.dor.role.dto.UserRoleRequestDTO;
import com.dor.role.repository.APICallLogRepository;
import com.dor.role.repository.ApplicationRepository;
import com.dor.role.repository.FunctionRepository;
import com.dor.role.repository.LoginRepository;
import com.dor.role.repository.RoleFunctionRepository;
import com.dor.role.repository.RoleRepository;
import com.dor.role.repository.UserRoleRepository;
import com.dor.role.service.RoleService;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;


@Service
public class RoleServiceImpl implements RoleService{

	@Autowired
	private APICallLogRepository apiCallLogRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private FunctionRepository functionRepository;
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	@Autowired
	private RoleFunctionRepository roleFunctionRepository;
	
	@Autowired
	private LoginRepository loginRepository;
	
	@Autowired
	private ApplicationRepository applicationRepository;
	
	@Override
	public AbstractResponseDTO sendOTP(AbstractRequestDTO requestDTO) throws Exception {
		ObjectToJsonMapper objectToJsonMapper=new ObjectToJsonMapper();
		APICallLogEntity apiCallLogEntity =new APICallLogEntity();
		apiCallLogEntity.setApiName("Send OTP");
		apiCallLogEntity.setRequestData(objectToJsonMapper.abstractRequestToJson(requestDTO));
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	    apiCallLogEntity.setTimestamp(ldt);
	    AbstractResponseDTO responseDTO=new AbstractResponseDTO();
		try
		{
			Optional<LoginEntity> loginEntityOptional= loginRepository.findById(requestDTO.getCreatorLoginId());
			LoginEntity loginEntity=loginEntityOptional.get();
			// Getting mobile no of user... used to send OTP
			if(loginEntity!=null)
			{
				String mobileNo="+91"+loginEntity.getMobNo();
				String tempOTP=""+getRandomNumber(100000,999999);
				String tempRef=""+getRandomNumber(1000,9999);
				loginEntity.setOtp(tempOTP);
				loginEntity.setOtpRequestNo(tempRef);
			    loginEntity.setOtpTimestamp(Timestamp.valueOf(LocalDateTime.now()));
			    loginEntity.setUpdatedOn(new Date());
			    loginEntity.setUpdationType("OTP generation");
			    loginEntity.setLastUpdationTime(ldt);
				Boolean status=sendOTP(tempOTP,tempRef,mobileNo);
				responseDTO.setStatus(status);
				if(status==true)
				{
					loginRepository.save(loginEntity);
					responseDTO.setMsg("OTP sent successfully!");
					responseDTO.setOtpRef(tempRef);
				}
				else
					responseDTO.setMsg("OTP Server is down or busy, Please try after some time!");
			}
			else
			{
				responseDTO.setMsg("Admin is not valid!");
				responseDTO.setStatus(false);
			}
		}
		catch(Exception e)
		{
			responseDTO.setMsg("Invalid admin, Please enter correct loginId!");
			responseDTO.setStatus(false);
		}
		apiCallLogEntity.setStatus(responseDTO.getStatus());
		apiCallLogEntity.setResponseData(objectToJsonMapper.abstractResponseToJson(responseDTO));
		apiCallLogRepository.save(apiCallLogEntity);
		return responseDTO;
	}
	
	@Override
	public AbstractResponseDTO createRole(AbstractRequestDTO requestDTO) throws Exception {
		// TODO Auto-generated method stub
		ObjectToJsonMapper objectToJsonMapper=new ObjectToJsonMapper();
		APICallLogEntity apiCallLogEntity =new APICallLogEntity();
		apiCallLogEntity.setApiName("Create a role");
		apiCallLogEntity.setRequestData(objectToJsonMapper.abstractRequestToJson(requestDTO));
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	    apiCallLogEntity.setTimestamp(ldt);
	    AbstractResponseDTO responseDTO=new AbstractResponseDTO();
	    try
	    {
	    	// Verification of the OTP
	    	Optional<LoginEntity> loginEntityOptional= loginRepository.findById(requestDTO.getCreatorLoginId());
			LoginEntity loginEntity=loginEntityOptional.get();
			if(loginEntity.getOtp().equals(requestDTO.getOtp()))
			{
				RoleEntity roleEntity = new RoleEntity();
				roleEntity.setRoleName(requestDTO.getRoleName());
				roleEntity.setRoleShortName(requestDTO.getRoleShortName());
				roleEntity.setAppId(requestDTO.getAppId());
				roleEntity.setStatus(true);
				roleEntity.setCreatedBy(requestDTO.getCreatorLoginId());
				roleEntity.setCreatedOn(ldt);
				roleEntity.setRoleDescription(requestDTO.getRoleDesc());
				responseDTO.setMsg("Role created successfully");
				responseDTO.setStatus(true);
				roleRepository.save(roleEntity);
			}
			else {
				responseDTO.setMsg("OTP didn't match!");
				responseDTO.setStatus(false);
			}
	    }
	    catch(Exception e)
	    {
	    	responseDTO.setMsg("Role creation failed");
	    	responseDTO.setStatus(false);
	    }
		apiCallLogEntity.setStatus(responseDTO.getStatus());
		apiCallLogEntity.setResponseData(objectToJsonMapper.abstractResponseToJson(responseDTO));
		apiCallLogRepository.save(apiCallLogEntity);
		return responseDTO;
	}
	
	@Override
	public AbstractResponseDTO createFunction(AbstractRequestDTO requestDTO) throws Exception {
		// TODO Auto-generated method stub
		ObjectToJsonMapper objectToJsonMapper=new ObjectToJsonMapper();
		APICallLogEntity apiCallLogEntity =new APICallLogEntity();
		apiCallLogEntity.setApiName("Create a function");
		apiCallLogEntity.setRequestData(objectToJsonMapper.abstractRequestToJson(requestDTO));
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	    apiCallLogEntity.setTimestamp(ldt);
	    AbstractResponseDTO responseDTO=new AbstractResponseDTO();
	    try
	    {
	    	// Verification of the OTP
	    	Optional<LoginEntity> loginEntityOptional= loginRepository.findById(requestDTO.getCreatorLoginId());
			LoginEntity loginEntity=loginEntityOptional.get();
			if(loginEntity.getOtp().equals(requestDTO.getOtp()))
			{
				FunctionEntity functionEntity = new FunctionEntity();
				functionEntity.setFunctionName(requestDTO.getFunctionName());
				functionEntity.setFunctionShortCode(requestDTO.getFunctionShortCode());
				functionEntity.setFunctionDescription(requestDTO.getFunctionDesc());
				functionEntity.setAppId(requestDTO.getAppId());
				functionEntity.setStatus(true);
				functionEntity.setCreatedBy(requestDTO.getCreatorLoginId());
				functionEntity.setCreatedOn(ldt);
				functionEntity.setFunctionURL(requestDTO.getFunctionShortCode());
				responseDTO.setMsg("Function created successfully");
				responseDTO.setStatus(true);
				functionRepository.save(functionEntity);
			}
			else {
				responseDTO.setMsg("OTP didn't match!");
				responseDTO.setStatus(false);
			}
	    }
	    catch(Exception e)
	    {
	    	responseDTO.setMsg("Function creation failed");
	    	responseDTO.setStatus(false);
	    }
		apiCallLogEntity.setStatus(responseDTO.getStatus());
		apiCallLogEntity.setResponseData(objectToJsonMapper.abstractResponseToJson(responseDTO));
		apiCallLogRepository.save(apiCallLogEntity);
		return responseDTO;
	}

	@Override
	public AbstractResponseDTO mapRoleFunction(RoleFunctionMapRequestDTO requestDTO) throws Exception {
		// TODO Auto-generated method stub
		ObjectToJsonMapper objectToJsonMapper=new ObjectToJsonMapper();
		APICallLogEntity apiCallLogEntity =new APICallLogEntity();
		apiCallLogEntity.setApiName("Mapping of role & function");
		apiCallLogEntity.setRequestData(objectToJsonMapper.roleFunctionMapRequestToJson(requestDTO));
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	    apiCallLogEntity.setTimestamp(ldt);
	    AbstractResponseDTO responseDTO=new AbstractResponseDTO();
	    try 
	    {
	    	for(RoleDataRequestDTO role: requestDTO.getRoleData())
	    	{
	    		for(FunctionDataRequestDTO function: requestDTO.getFunctionData())
	    		{
	    			RoleFunctionEntity roleFunctionEntity= new RoleFunctionEntity();
	    			roleFunctionEntity.setRoleSerialNo(role.getSerialNo());
	    			roleFunctionEntity.setRoleName(role.getRoleName());
	    			roleFunctionEntity.setFunctionSerialNo(function.getSerialNo());
	    			roleFunctionEntity.setFunctionName(function.getFunctionName());
	    			roleFunctionEntity.setAppId(requestDTO.getAppId());
	    			roleFunctionEntity.setCreatedBy(requestDTO.getCreatorLoginId());
	    			roleFunctionEntity.setCreatedOn(ldt);
	    			roleFunctionEntity.setStatus(true);
	    			responseDTO.setMsg("Role & Function mapped successfully");
					responseDTO.setStatus(true);
					roleFunctionRepository.save(roleFunctionEntity);
	    		}
	    	}
		} 
	    catch (Exception e) 
	    {
	    	responseDTO.setStatus(false);
	    	responseDTO.setMsg("Role & function mapping failed");
		}
		apiCallLogEntity.setStatus(responseDTO.getStatus());
		apiCallLogEntity.setResponseData(objectToJsonMapper.abstractResponseToJson(responseDTO));
		apiCallLogRepository.save(apiCallLogEntity);
		return responseDTO;
	}
	
	@Override
	public UserRoleMapResponseDTO mapUserRole(UserRoleMapRequestDTO requestDTO) throws Exception {
		// TODO Auto-generated method stub
		ObjectToJsonMapper objectToJsonMapper=new ObjectToJsonMapper();
		APICallLogEntity apiCallLogEntity =new APICallLogEntity();
		apiCallLogEntity.setApiName("Mapping of user & role");
		apiCallLogEntity.setRequestData(objectToJsonMapper.userRoleMapRequestToJson(requestDTO));
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	    apiCallLogEntity.setTimestamp(ldt);
	    UserRoleMapResponseDTO responseDTO=new UserRoleMapResponseDTO();
	    List<UserRoleMapDTO> userRoleMapList = new LinkedList<UserRoleMapDTO>();
	    try 
	    {
	    	List<RoleFunctionEntity> roleFunctionEntities= roleFunctionRepository.findByRoleSerialNo(requestDTO.getRoleSerialNo());
	    	for(RoleFunctionEntity roleFunctionEntity: roleFunctionEntities)
	    	{
	    		FunctionEntity functionEntity= functionRepository.findBySerialNo(roleFunctionEntity.getFunctionSerialNo());
	    		UserRoleEntity userRoleEntity= new UserRoleEntity();
	    		userRoleEntity.setLoginId(requestDTO.getLoginId());
	    		userRoleEntity.setRoleId(requestDTO.getRoleSerialNo());
	    		userRoleEntity.setAppId(requestDTO.getAppId());
	    		userRoleEntity.setEnabled(true);
	    		
	    		UserRoleMapDTO userRoleMapDTO= new UserRoleMapDTO();
	    		userRoleMapDTO.setAppId(requestDTO.getAppId());
	    		userRoleMapDTO.setFunctionId(functionEntity.getSerialNo());
	    		userRoleMapDTO.setFunctionURL(functionEntity.getFunctionURL());
	    		userRoleMapDTO.setLoginId(requestDTO.getLoginId());
	    		userRoleMapDTO.setRoleId(requestDTO.getRoleSerialNo());
	    		userRoleMapList.add(userRoleMapDTO);
	    		
	    		userRoleRepository.save(userRoleEntity);
	    	}
	    	responseDTO.setUserRoleMapList(userRoleMapList);
	    	responseDTO.setMsg("User & Role Mapped successfully!");
	    	responseDTO.setStatus(true);
		} 
	    catch (Exception e) 
	    {
	    	responseDTO.setStatus(false);
	    	responseDTO.setMsg("user & role mapping failed");
		}
		apiCallLogEntity.setStatus(responseDTO.getStatus());
		apiCallLogEntity.setResponseData(objectToJsonMapper.userRoleMapResponseToJson(responseDTO));
		apiCallLogRepository.save(apiCallLogEntity);
		return responseDTO;
	}
	
	
	public int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }
	
	public Boolean sendOTP(String otp, String ref,String mobNo)
	{
		Twilio.init(APIConstants.ACCOUNT_SID, APIConstants.AUTH_TOKEN);
		String msg="OTP for verification for creation of role on CBN is:"+otp+" for request no Ref"+ref+" .The otp expires within 10 mins.";
		try
		{
			com.twilio.rest.api.v2010.account.Message.creator(new PhoneNumber(mobNo),new PhoneNumber(APIConstants.FROM_NUMBER), msg).create();
		}
		catch(Exception e)
		{
			System.out.println("OTP sent failed!");
			return false;
		}
		return true;
	}

	@Override
	public AbstractFetchResponseDTO fetchData(AbstractFetchRequestDTO requestDTO) throws Exception {
		// TODO Auto-generated method stub
		ObjectToJsonMapper objectToJsonMapper=new ObjectToJsonMapper();
		APICallLogEntity apiCallLogEntity =new APICallLogEntity();
		apiCallLogEntity.setApiName("Fetch role & function data");
		apiCallLogEntity.setRequestData(objectToJsonMapper.abstractFetchRequestToJson(requestDTO));
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	    apiCallLogEntity.setTimestamp(ldt);
	    AbstractFetchResponseDTO responseDTO=new AbstractFetchResponseDTO();
	    try {
			List<RoleEntity> roleDataEntities=roleRepository.findByAppId(requestDTO.getAppId());
			List<FunctionEntity> functionDataEntities=functionRepository.findByAppId(requestDTO.getAppId());
			responseDTO.setFunctionData(functionDataEntities);
			responseDTO.setRoleData(roleDataEntities);
			responseDTO.setMsg("Data fetched successfully!");
			responseDTO.setStatus(true);
		} 
	    catch (Exception e) {
			// TODO: handle exception
	    	responseDTO.setMsg("Data fetching failed!");
	    	responseDTO.setStatus(false);
		}
	    apiCallLogEntity.setStatus(responseDTO.getStatus());
		apiCallLogEntity.setResponseData(objectToJsonMapper.abstractFetchResponseToJson(responseDTO));
		apiCallLogRepository.save(apiCallLogEntity);
		return responseDTO;
		
	}
	
	@Override
	public ApplicationListResponseDTO fetchApplicationData() throws Exception {
		// TODO Auto-generated method stub
		ObjectToJsonMapper objectToJsonMapper=new ObjectToJsonMapper();
		APICallLogEntity apiCallLogEntity =new APICallLogEntity();
		apiCallLogEntity.setApiName("Fetch application data");
		apiCallLogEntity.setRequestData("");
		Instant instant = Instant.ofEpochMilli(new Date().getTime());
	    LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
	    apiCallLogEntity.setTimestamp(ldt);
	    ApplicationListResponseDTO responseDTO=new ApplicationListResponseDTO();
	    List<ApplicationListDTO> applicationList= new LinkedList<ApplicationListDTO>();
	    try {
			List<ApplicationEntity> applicationDataEntities=applicationRepository.findByEnabled(true);
			for(ApplicationEntity ae: applicationDataEntities)
			{
				ApplicationListDTO applicationListDTO= new ApplicationListDTO();
				applicationListDTO.setAppId(ae.getAppId());
				applicationListDTO.setAppName(ae.getAppName());
				applicationListDTO.setAppShortCode(ae.getAppShortCode());
				applicationList.add(applicationListDTO);
			}
			responseDTO.setApplicationList(applicationList);
			responseDTO.setMsg("Data fetched successfully!");
			responseDTO.setStatus(true);
		} 
	    catch (Exception e) {
			// TODO: handle exception
	    	responseDTO.setMsg("Data fetching failed!");
	    	responseDTO.setStatus(false);
		}
	    apiCallLogEntity.setStatus(responseDTO.getStatus());
		apiCallLogEntity.setResponseData(objectToJsonMapper.applicationListResponseToJson(responseDTO));
		apiCallLogRepository.save(apiCallLogEntity);
		return responseDTO;
	}

}
