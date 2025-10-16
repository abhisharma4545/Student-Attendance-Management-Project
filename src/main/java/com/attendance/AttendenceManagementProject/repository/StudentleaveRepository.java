package com.attendance.AttendenceManagementProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.AttendenceManagementProject.Model.StudentLeave;

@Repository
public interface StudentleaveRepository extends JpaRepository<StudentLeave, Integer>{
	
	  public List<StudentLeave> findAllByStudent_Uname(String uname);

}
