package com.attendance.AttendenceManagementProject.Controller;

import jakarta.servlet.http.HttpSession;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.attendance.AttendenceManagementProject.Model.Attendance;
import com.attendance.AttendenceManagementProject.Model.Inquiry;
import com.attendance.AttendenceManagementProject.Model.Student;
import com.attendance.AttendenceManagementProject.Model.StudentFeedback;
import com.attendance.AttendenceManagementProject.Model.StudentLeave;
import com.attendance.AttendenceManagementProject.ServiceIml.InquiryServiceImpl;
import com.attendance.AttendenceManagementProject.ServiceIml.StudentFeedbackServcieImpl;
import com.attendance.AttendenceManagementProject.ServiceIml.StudentServiceImpl;
import com.attendance.AttendenceManagementProject.ServiceIml.StudentleaveServiceImpl;

@Controller 
public class StudentController {
	
	@Autowired
	public StudentServiceImpl stuSer;
	
	@Autowired
	public StudentleaveServiceImpl leaveser;
	
	@Autowired
	public StudentFeedbackServcieImpl serfb;
	
	@Autowired
	public InquiryServiceImpl seriq;
	
	@GetMapping("/student_login")
	public String studentLoginPage(Model model) {
		return "student_login";
	}
	
	@PostMapping("/student-validate")
	public String studentValidate(@RequestParam("uname")String uname,@RequestParam("password")String password, Model model,HttpSession session){
		
		 
		  boolean bn=  stuSer.Validate(uname, password);
		 if(bn) {
		Student student= stuSer.getstudentByUname(uname);
		 session.setAttribute("uname",student.getUname());
		 List<Attendance> li = student.getAttendanceList();
         long tnp =   li.stream().filter(x->x.getAttendance().equals("present")).count();
         long tna =  li.stream().filter(x->x.getAttendance().equals("absent")).count();
         student.setTotalPresentDays(tnp);
         student.setTotalAbsentDays(tna);
         stuSer.registerStudent(student);
         model.addAttribute("student",student);
		return "student-dashboard";
		 }else {
			 model.addAttribute("error","invalid user credential" );
			 return "student_login";
			 
			 
		 }
	}
	
	   @GetMapping("/student/profile")
	   public String studentprofile(Model model) {
		   
		   Student student = stuSer.getloggedstu();
		   model.addAttribute("student", student);
		   return "student_profile";
	   }
	   
	   @GetMapping("/student/change-password")
	   public String stupasschange() {
		   return "change_student_password";
		   
	   }
	   
	   @GetMapping("/student/feedback")
	   public String stufeedback() {
		   return "student_feedback";
		   
	   }
	   
	   
	   @GetMapping("/student/complaint-result")
	   public String complaintIssueRes(Model model) {
		      Long id =  stuSer.getloggedstu().getStudentId();
		         List<Inquiry> iq = seriq.getlistofStudent(id);
		         model.addAttribute("inquiries", iq);
		   return "comlaint_issue_result";
	   }
	   
	   @GetMapping("/student/leave/apply")
	   public String applyforleave() {
		   return "apply_for_leave";
	   }
	   
	   @GetMapping("/student/leave/status")
	   public String leaveStatus(Model model) {
		   Student stu = stuSer.getloggedstu();
		  List<StudentLeave> leave = leaveser.getStudentLeave(stu.getUname());
		  model.addAttribute("leaves", leave);
		   return "leave_status_report";
	   }
	   
	   @PostMapping("/change-password")
	   public String changepassword(@RequestParam("oldPassword")String oldpassword,@RequestParam("newPassword")String newpassword,Model model) {
		   
		  Student stu = stuSer.getloggedstu();
		   if(oldpassword.equals(stu.getPassword())) {
			        stu.setPassword(newpassword);
			        stuSer.registerStudent(stu);
			        model.addAttribute("successMessage", "Your password changed successfully");
			        return "change_student_password";
			        
		   }else {
			   model.addAttribute("errorMessage", "your old password is not match");
			   return "change_student_password";
			   
		   } 
	   }
	   
	   @PostMapping("/apply-leave")
	   public String applyleave(@RequestParam("message")String leaveReason,@RequestParam("days") int noOfdays,Model model) {
		    Student student = stuSer.getloggedstu();
		    
		    StudentLeave stuLeave = new StudentLeave();
		     
		     stuLeave.setLeaveApplyDate(LocalDate.now());
		    stuLeave.setLeaveReason(leaveReason);
		    stuLeave.setName(student.getStudName());
		    stuLeave.setNoOfDays(noOfdays);
		    stuLeave.setStudent(student);
		   boolean bn= leaveser.saveLeave(stuLeave);
		   if(bn) {
			     model.addAttribute("success", "leave  apply succesfully");
			     return "apply_for_leave";
		   }else {
			   model.addAttribute("error","leave not applied");
			   return "apply_for_leave";
		   }   
		   
	   }
	   
	   @PostMapping("/save/feedback")
	   public String savefeddback(@RequestParam("fullName")String fullname,@RequestParam("mobile") String mobile,@RequestParam("message")String feedback,Model model){
		 Student stu = stuSer.getloggedstu();
		 StudentFeedback fb = new StudentFeedback();
		 fb.setFeedback(feedback);
		 fb.setFullName(fullname);
		 fb.setMobile(mobile);
		 fb.setStudent(stu);
		 boolean bn =serfb.saveFeedback(fb);
		 if(bn) {
		   model.addAttribute("successMessage", "feedBack submitted succesfully");
		 }else {
			 model.addAttribute("errorMessage", "feedBack not submitted ");
			 
		 }
		 return "student_feedback";
	   }
	   
	   
	   @GetMapping("/student/inquiry")
	   public String stuInqauiry(Model model) {
		   Inquiry iq = new Inquiry();
		   model.addAttribute("studentQuery", iq);
		   return "student_inquiry";
	   }
	   
	   @PostMapping("/student/query")
	   public String studentinquiryQuery(@ModelAttribute("studentQuery")Inquiry iq,Model model) {
		   Student student = stuSer.getloggedstu();
		   iq.setStudent(student);
		  boolean bn = seriq.savequery(iq);
		  if(bn) {
			  model.addAttribute("inquiryMessage", "query submitted successfully");
		  }else {
			  
		  }
		      
		   return "student_inquiry";
	   }
	   
	   @GetMapping("/student/attendance")
	   public String studentrepotspecific(Model model) {
		       Student stu =stuSer.getloggedstu();
               List<Student> list = new ArrayList<Student>();
               list.add(stu);
                model.addAttribute("report", list);
		      return "attendance_reports";
		  
	   }
}
