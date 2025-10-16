package com.attendance.AttendenceManagementProject.Service;

import java.time.LocalDate;

import com.attendance.AttendenceManagementProject.Model.Attendance;

public interface AttendanceService {
	
	    
	
	 public boolean saveAtt(Attendance attendance);
	 public boolean existByDate(LocalDate systemDate);

}
