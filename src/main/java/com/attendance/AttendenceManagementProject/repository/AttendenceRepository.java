package com.attendance.AttendenceManagementProject.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.AttendenceManagementProject.Model.Attendance;
import com.attendance.AttendenceManagementProject.Model.AttendanceId;

@Repository
public  interface AttendenceRepository extends JpaRepository<Attendance, AttendanceId> {
	
	  boolean existsById_SystemDate(LocalDate systemDate);
	  
	  int countByIdStudentIdAndAttendance(Long studentId, String attendance);

		  

}
