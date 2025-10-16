package com.attendance.AttendenceManagementProject.ServiceIml;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.AttendenceManagementProject.Model.StudentLeave;
import com.attendance.AttendenceManagementProject.Service.StudentleaveService;
import com.attendance.AttendenceManagementProject.repository.StudentleaveRepository;

@Service
public class StudentleaveServiceImpl implements StudentleaveService {
	
	
	@Autowired
	public StudentleaveRepository leaverepo;

	@Override
	public boolean saveLeave(StudentLeave stuLeave) {
		// TODO Auto-generated method stub
		 StudentLeave  obj= leaverepo.save(stuLeave);
		return obj !=null;
	}

	@Override
	public List<StudentLeave> getStudentLeave(String uname) {
		// TODO Auto-generated method stub
           List<StudentLeave> leave =  leaverepo.findAllByStudent_Uname(uname);
		return leave;
	}
	       
	
	      
	          
	

}
