package com.attendance.AttendenceManagementProject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.attendance.AttendenceManagementProject.Model.Admin;

@Repository
public interface  AdminRepository extends JpaRepository<Admin, Integer>{
	
	
            public Admin findByUnameAndPassword(String uname, String password);
            
            public boolean existsByUname(String uname);
}
