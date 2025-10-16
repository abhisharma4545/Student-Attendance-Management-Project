package com.attendance.AttendenceManagementProject.ServiceIml;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.AttendenceManagementProject.Model.Inquiry;
import com.attendance.AttendenceManagementProject.Model.Student;
import com.attendance.AttendenceManagementProject.Service.InquiryService;
import com.attendance.AttendenceManagementProject.repository.InquiryRepository;

@Service
public class InquiryServiceImpl implements  InquiryService{
	
	@Autowired
	public InquiryRepository iqrepo;

	@Override
	public boolean savequery(Inquiry iq) {
		// TODO Auto-generated method stub
		Inquiry inq = iqrepo.save(iq);
		return inq !=null;
	}

	@Override
	public Inquiry getInq(int id) {
		// TODO Auto-generated method stub
		 Optional<Inquiry> iq  = iqrepo.findById(id);
		return iq.get();
	}

	@Override
	public List<Inquiry> getlistofStudent(Long id) {
		// TODO Auto-generated method stub
		 List<Inquiry> li=iqrepo.findAllByStudent_StudentId(id);
	    	return li ;
	}

	

}
