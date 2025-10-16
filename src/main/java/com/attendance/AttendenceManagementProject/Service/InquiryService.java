package com.attendance.AttendenceManagementProject.Service;

import java.util.List;
import java.util.Optional;

import com.attendance.AttendenceManagementProject.Model.Inquiry;
import com.attendance.AttendenceManagementProject.Model.Student;

public interface InquiryService {
	
	
	public boolean savequery(Inquiry iq);
	
	public Inquiry getInq(int id);
	
	public List<Inquiry> getlistofStudent(Long id);

}
