package com.attendance.AttendenceManagementProject.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.attendance.AttendenceManagementProject.Model.Student;

@Repository
public interface StudentRepository  extends JpaRepository<Student,Long>{
    
	   @Query("SELECT s.email FROM Student s WHERE s.studentId = :id")
       public  String findEmailByStudentId(@Param("id") Long id);
	   
	    public Optional<Student> findByUname(String uname);
	   
	    public Student findByUnameAndPassword(String uname,String password);
	    
	    public boolean existsByUname(String uname);
	   
	  
	   
	   
	        
}
