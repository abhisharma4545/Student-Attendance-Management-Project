package com.attendance.AttendenceManagementProject.ServiceIml;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.AttendenceManagementProject.Model.Attendance;
import com.attendance.AttendenceManagementProject.Service.AttendanceService;
import com.attendance.AttendenceManagementProject.repository.AttendenceRepository;

@Service
public class AttendanceServiceImpl implements AttendanceService {
	
	@Autowired
	public AttendenceRepository attRepo;

	@Override
	public boolean saveAtt(Attendance attendance) {
		// TODO Auto-generated method stub
		 Attendance att =  attRepo.save(attendance);
		return att != null;
	}

	@Override
	public boolean existByDate(LocalDate systemDate) {
		// TODO Auto-generated method stub
		 boolean bn= attRepo.existsById_SystemDate(systemDate);
		 return bn;
	}

	
	
	
}
