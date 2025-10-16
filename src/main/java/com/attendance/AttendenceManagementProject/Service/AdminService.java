package com.attendance.AttendenceManagementProject.Service;

import org.springframework.stereotype.Service;
import com.attendance.AttendenceManagementProject.Model.Admin;


@Service
public interface AdminService  {
	
	
	
	     public boolean SaveAdmin(Admin admin);
	     
	     public boolean isValidate(String uname,String password);
	     
	       public Admin  getloggedAdmin();
	       
	       public boolean alreadyexistUsername(String uname);
	    
	   
		   
	   

}
