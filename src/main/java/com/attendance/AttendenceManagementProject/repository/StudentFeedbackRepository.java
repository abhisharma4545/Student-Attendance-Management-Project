package com.attendance.AttendenceManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.AttendenceManagementProject.Model.StudentFeedback;

@Repository
public interface StudentFeedbackRepository extends JpaRepository<StudentFeedback, Integer>{

	
}
