package com.attendance.AttendenceManagementProject.ServiceIml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.AttendenceManagementProject.Model.StudentFeedback;
import com.attendance.AttendenceManagementProject.Service.StudentFeedbackService;
import com.attendance.AttendenceManagementProject.repository.StudentFeedbackRepository;

@Service
public class StudentFeedbackServcieImpl  implements StudentFeedbackService{
	
	@Autowired
	public StudentFeedbackRepository repofb;

	@Override
	public boolean saveFeedback(StudentFeedback feedback) {
		// TODO Auto-generated method stub
	    StudentFeedback	fb=repofb.save(feedback);
		return fb != null;
	}

}
