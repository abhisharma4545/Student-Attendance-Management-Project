package com.attendance.AttendenceManagementProject.Service;

import java.util.List;
import java.util.Optional;

import com.attendance.AttendenceManagementProject.Model.Student;

public interface StudentService {

	public boolean registerStudent(Student student);
	
	public String deleteStudentById(Long id);
	
	 public List<Student> getAllStudents();
	 
	 public void deleteAllselectedStu(List<Long> stuId);
	 
	 public String getstudentEmailById(Long id);
	 
	 public Optional<Student> getstudentBYId(Long id);
	 
	 public Student getstudentByUname(String uname);
	   
	 public boolean Validate(String uname,String password);
		 
	 public Student getloggedstu();
	 
	 public boolean alreadyexistUname(String uname);
	 
		
	
}
