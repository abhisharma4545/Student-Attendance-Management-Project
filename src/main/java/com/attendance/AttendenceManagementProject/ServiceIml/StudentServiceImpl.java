package com.attendance.AttendenceManagementProject.ServiceIml;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.attendance.AttendenceManagementProject.Model.Student;
import com.attendance.AttendenceManagementProject.Service.StudentService;
import com.attendance.AttendenceManagementProject.repository.StudentRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	public StudentRepository stuRepo;
	
	@Autowired
	public HttpSession session;
     

	@Override
	public boolean registerStudent(Student student) {
		// TODO Auto-generated method
		   
		     Student s= stuRepo.save(student);
		    return s != null;
	}

	@Override
	public String deleteStudentById(Long id) {
		// TODO Auto-generated method stub
		  Optional<Student> stu =stuRepo.findById(id);
		  Student s= stu.orElseThrow(()->new RuntimeException("User id not found exception"));
		  stuRepo.deleteById(id);
		  return "User successfully deleted";
		  
		
	}

	@Override
	public List<Student> getAllStudents() {
		   List<Student> li =   stuRepo.findAll();
		return li;
	}

	@Override
	public void deleteAllselectedStu(List<Long> stuId) {
		// TODO Auto-generated method stub
		 stuRepo.deleteAllById(stuId);
		
	}

	@Override
	public String getstudentEmailById(Long id) {
		// TODO Auto-generated method stub
		String email =   stuRepo.findEmailByStudentId(id);
		return email;
	}

	@Override
	public Optional<Student> getstudentBYId(Long id) {
		// TODO Auto-generated method stub
		   Optional<Student> stu= stuRepo.findById(id);
		return stu;
	}

	@Override
	public Student getstudentByUname(String uname) {
		// TODO Auto-generated method stub
		 Optional<Student> stu= stuRepo.findByUname(uname);
		return stu.get();
	}

	@Override
	public boolean Validate(String uname, String password) {
		// TODO Auto-generated method stub
		Student stu =  stuRepo.findByUnameAndPassword(uname, password);
		if(stu != null) {
			if(password.equals(stu.getPassword())) {
				session.setAttribute("loggedstu", stu);
				return true;
			}else {
				return false;
			}
			
		}else {
			return false;
		}
	}
	
	@Override
	public Student getloggedstu() {
		return (Student)session.getAttribute("loggedstu");
		
	}

	@Override
	public boolean alreadyexistUname(String uname) {
		// TODO Auto-generated method stub
		return  stuRepo.existsByUname(uname);
	}

	

}
