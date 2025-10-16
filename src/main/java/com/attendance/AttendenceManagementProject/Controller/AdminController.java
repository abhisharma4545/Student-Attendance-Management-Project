package com.attendance.AttendenceManagementProject.Controller;


import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import com.attendance.AttendenceManagementProject.Model.Admin;
import com.attendance.AttendenceManagementProject.Model.Student;
import com.attendance.AttendenceManagementProject.ServiceIml.AdminServiceIml;
import com.attendance.AttendenceManagementProject.ServiceIml.AttendanceServiceImpl;
import com.attendance.AttendenceManagementProject.ServiceIml.StudentServiceImpl;

import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
public class AdminController{
	
	@Autowired
	 public AdminServiceIml adminserve;
	
	@Autowired
	public StudentServiceImpl stuser;
	
	@Autowired
	public AttendanceServiceImpl attser;

   
	@GetMapping("/index")
	public String index(Model model) {
		model.addAttribute("adminregister",new Admin());
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
		model.addAttribute("currentTime",time);
		model.addAttribute("showSignup", false);
		return "index";	
	}
	
	
	@GetMapping("/student/registration")
	public String studentregiter(Model model ) {
		model.addAttribute("student", new Student());
		return "student_register";
	}
	
	@GetMapping("/student/feedback/info")
	public String studentfeed() {
		return "student_feedback_information";
		
	}
	
	@GetMapping("/student/send-credentials")
    public String showStudentList(Model model) {
          List<Student> li= stuser.getAllStudents();
          model.addAttribute("students", li);
        return "send_user_name_and_password_to_student_in_mail"; // Thymeleaf template name
    }
	
	@GetMapping("/student/passwords/single")
    public String studentpasswordgenInd() {
      
        return "student_password_generation_individually"; // Thymeleaf template name
    }
	
	@GetMapping("/student/passwords/all")
    public String studentpasswordgenDynmic() {
      
        return "student_password_generation_dynamically"; // Thymeleaf template name
    }
	
	@GetMapping("/student/remove")
	public String removestu(Model model) {
		
		List<Student> li=stuser.getAllStudents();
		 model.addAttribute("students", li);
		return "remove_student";
	}
	
	@GetMapping("/student/notifications")
	public String studentnoti() {
		return "student_notification";
	}
	
	@GetMapping("/student/manage")
	public String managestu(Model model) {
		
		   List<Student> li= stuser.getAllStudents();
		    model.addAttribute("students", li);
		    
		return "manage_student";
	}
	
	
	
	@GetMapping("/reports/daily")
	public String checkdailyAttendReport(Model model) {
		Student student= new Student();
		List<Student> list = new ArrayList<Student>();
		 
		model.addAttribute("attendanceList", list);
		return "check_daily_attendence_report";
	}
	
	@GetMapping("/admin/profile")
	public String adminprofile(Model model,HttpSession session) {
		System.out.println("admin profile controller");
		 Admin admin=  adminserve.getloggedAdmin();
		
		model.addAttribute("admin", admin);
		return "admin_profile";
	}
	
	
	@GetMapping("/admin/change-password")
	public String adminchangepass() {
		return "admin_change_password";
	}
	
	@GetMapping("/logout")
    public String logout(HttpSession session) {
        // Destroy the session
        session.invalidate();
        // Redirect to login page (index.html)
        return "redirect:/index";
    }
	
	@GetMapping("/attendance/take")
	public String Takeattendenmce(Model model) {
		 List<Student> li =stuser.getAllStudents();
		 model.addAttribute("students", li);  
		 model.addAttribute("activeCount", li.size());
		 
		 boolean bn = attser.existByDate(LocalDate.now()); 
		 model.addAttribute("alreadyTaken", bn);
		 return "attendance";
	}
	
	
	@GetMapping("/attendance/today")
	public String todaysAttendence() {
		return "check_todays_attendence";
	}
	
	
	@GetMapping("/attendance/daily")
	public String checkdailyAttendece() {
		return "check_daily_attendence_reports";
	}
	
	@GetMapping("/leave/new")
	public String newleaves() {
		return "manage_student_leaves";
	}
	
	@GetMapping("/leave/approved")
	public String approvedleaves() {
		return "approved_student_leaves_request";
	}
	

   
	@GetMapping("/leave/rejected")
	public String rejectedleaves() {
		return "rejected_student_leaves_request";
	}
	
	@GetMapping("reports/attendance")
	public String attendeceReports(Model model) {
		
		List<Student> stu =stuser.getAllStudents();
		model.addAttribute("report", stu);
		model.addAttribute("count", stu.size());
		return "attendance_reports";
	}
	
	@GetMapping("/attendence/reports/daily")
	public String attendeceDaily() {
		return "check_daily_attendence";
	}
	
	@GetMapping("/reports/monthly")
	public String monthlyAttendence(Model model) {
		
	//	model.addAttribute("reportsFilter", filter);
		Student student = new Student();
		List<Student> list= new ArrayList<Student>();
		list.add(student);
		
		model.addAttribute("reports", list);
		return "monthly_attendence_report";
	}
	
	
	
}
