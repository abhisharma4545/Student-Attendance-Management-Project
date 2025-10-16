package com.attendance.AttendenceManagementProject.ServiceIml;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.AttendenceManagementProject.Model.Admin;
import com.attendance.AttendenceManagementProject.Service.AdminService;
import com.attendance.AttendenceManagementProject.repository.AdminRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceIml  implements AdminService{
	
	@Autowired
	 public AdminRepository userrepo;
	
	
	
	@Autowired
	 public HttpSession session;
	

	@Override
	public boolean isValidate(String uname, String password) {
		// TODO Auto-generated method stub
		Admin admin =userrepo.findByUnameAndPassword(uname, password);
		   if(admin !=null) {
			   session.setAttribute("loggedAdmin", admin);
		   }
		return admin != null  && (uname.equals(admin.getUname()) && password.equals(admin.getPassword()));
	}

	@Override
	     public boolean SaveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		  if(admin !=null) {
			   Admin ad= userrepo.save(admin); 
			   return ad != null;
		   }
		  return false;
		   
	}

	@Override
	public Admin getloggedAdmin() {
		// TODO Auto-generated method stub
		   
		return (Admin) session.getAttribute("loggedAdmin");
	}

	@Override
	public boolean alreadyexistUsername(String uname) {
		// TODO Auto-generated method stub
		return userrepo.existsByUname(uname);
	}
	
      
	

	}

	


