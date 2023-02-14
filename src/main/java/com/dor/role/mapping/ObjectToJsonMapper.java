package com.dor.role.mapping;

import java.io.IOException;

import com.dor.role.dto.AbstractFetchRequestDTO;
import com.dor.role.dto.AbstractFetchResponseDTO;
import com.dor.role.dto.AbstractRequestDTO;
import com.dor.role.dto.AbstractResponseDTO;
import com.dor.role.dto.ApplicationListResponseDTO;
import com.dor.role.dto.RoleFunctionMapRequestDTO;
import com.dor.role.dto.UserRoleMapRequestDTO;
import com.dor.role.dto.UserRoleMapResponseDTO;
import com.dor.role.dto.UserRoleRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectToJsonMapper {
	
	public String abstractRequestToJson(AbstractRequestDTO requestDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(requestDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}
	
	public String abstractResponseToJson(AbstractResponseDTO responseDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(responseDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}
	
	public String abstractFetchRequestToJson(AbstractFetchRequestDTO requestDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(requestDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}
	
	public String abstractFetchResponseToJson(AbstractFetchResponseDTO responseDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(responseDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}
	
	public String applicationListResponseToJson(ApplicationListResponseDTO responseDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(responseDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}
	
	public String roleFunctionMapRequestToJson(RoleFunctionMapRequestDTO requestDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(requestDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}
	
	public String userRoleMapRequestToJson(UserRoleMapRequestDTO requestDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(requestDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}
	
	public String userRoleMapResponseToJson(UserRoleMapResponseDTO responseDTO)
	{
		ObjectMapper Obj = new ObjectMapper();  
        try {  
            String jsonObject = Obj.writeValueAsString(responseDTO); 
            return jsonObject;
        }  
        catch (IOException e) {  
            return null;
        }  
	}

}
