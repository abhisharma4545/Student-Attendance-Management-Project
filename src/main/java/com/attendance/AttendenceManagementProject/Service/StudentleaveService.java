package com.attendance.AttendenceManagementProject.Service;


import java.util.List;

import com.attendance.AttendenceManagementProject.Model.StudentLeave;

public interface StudentleaveService {
	
	      
      public boolean saveLeave(StudentLeave stuLeave);
      
      public List<StudentLeave> getStudentLeave(String uname);
    	  
      
}
