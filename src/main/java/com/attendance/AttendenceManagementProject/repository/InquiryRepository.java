package com.attendance.AttendenceManagementProject.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.AttendenceManagementProject.Model.Inquiry;

@Repository
public interface InquiryRepository  extends JpaRepository<Inquiry, Integer>{
	
	  public List<Inquiry> findAllByStudent_StudentId(Long id);
 
}
