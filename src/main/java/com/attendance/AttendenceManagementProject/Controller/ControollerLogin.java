package com.attendance.AttendenceManagementProject.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.attendance.AttendenceManagementProject.Model.Admin;
import com.attendance.AttendenceManagementProject.Model.Attendance;
import com.attendance.AttendenceManagementProject.Model.AttendanceId;
import com.attendance.AttendenceManagementProject.Model.Student;
import com.attendance.AttendenceManagementProject.ServiceIml.AdminServiceIml;
import com.attendance.AttendenceManagementProject.ServiceIml.AttendanceServiceImpl;
import com.attendance.AttendenceManagementProject.ServiceIml.EmailServiceimpl;
import com.attendance.AttendenceManagementProject.ServiceIml.StudentServiceImpl;



@Controller
public class ControollerLogin {
	
	@Autowired
	public AdminServiceIml adminser;
	@Autowired
	public StudentServiceImpl stuSer;
	
	@Autowired
	public EmailServiceimpl  emailservice;
	
	@Autowired
	public AttendanceServiceImpl attser;
	
	@PostMapping("/adminLogin")
	public String admindashboard(@RequestParam("uname") String uname,@RequestParam("password") String password,Model model) {
		 model.addAttribute("currentTime", new java.util.Date());   
		    boolean bn=   adminser.isValidate(uname, password);
		    
		    if(bn) {
		    	   
		    	model.addAttribute("uname", uname);
		         return "admin-dashboard";
		    }else {
		    	model.addAttribute("credential", "Invalid username or password!");
		    	model.addAttribute("adminregister", new Admin());
		    	model.addAttribute("showSignup", false);
		    	return "index";
		    }	    
	}
	

	   @PostMapping("/admin-register")
	   public String saveAdmin(@ModelAttribute("adminregister") Admin admin, Model model) {
		    if(adminser.alreadyexistUsername(admin.getUname())) {
		    	model.addAttribute("signupError", "Username already exists!");
	            model.addAttribute("showSignup", true); // show signup box
	            model.addAttribute("adminregister", new Admin());
	            return "index";
		    }
		    boolean bn = adminser.SaveAdmin(admin);
		   model.addAttribute("adminregister", new Admin());
		   if(bn) {
			  model.addAttribute("messageSuccess","admin sign up successfully" ); 
			  
		  }else {
			  model.addAttribute("error", "admin is not sign up");
		  
		  }
		   return "index";
		  
	   }
	   
	   @PostMapping("/student/register")
	   public String registerStudent(@ModelAttribute Student student,Model model) {
		       if(stuSer.alreadyexistUname(student.getUname())){
		    	   model.addAttribute("", new Student());
		    	   model.addAttribute("errorUname", "Username already exists !");
		    	   return "student_register";
		       }
		      student.setStatus(1);
		     boolean bn = stuSer.registerStudent(student);
		     if(bn) {
		    	model.addAttribute("successMessage", "student register successfully");
		    	return "student_register";
		    }else {
		    	model.addAttribute("failMessage", "student not register");
		    	return "student_register";
		    	
		    }
		   
	   }
	   
	   @GetMapping("/sign-up")
	   public String signup(Model model) {
		   model.addAttribute("admin", new Admin());
		   return "index";
	   }
	   
	   @GetMapping("/students")
	   public String getNoOfStudent(Model model) {
		    
		  List<Student> li =  stuSer.getAllStudents();
		    int count = li.size();
		    model.addAttribute("students", count);
		    return "admin-dashboard";    
	   }
	   
	   @PostMapping("/students/delete")
	   public String removeStudents(@RequestParam("record") List<Long> studentIds,
	                                RedirectAttributes redirectAttributes, Model model) {
	       try {
	           // delete all students by their IDs
	             stuSer.deleteAllselectedStu(studentIds);
	            List<Student> li =   stuSer.getAllStudents();
	            model.addAttribute("students", li);
	           redirectAttributes.addFlashAttribute("messageSuccess", "Selected students deleted successfully!");
	       } catch (Exception e) {
	           redirectAttributes.addFlashAttribute("messageFailure", "Error deleting students: " + e.getMessage());
	       }
	       return "remove_student";  // redirect back to page
	   }
	   
	           
	       @GetMapping("admin/send-email/{id}")
	       public String sendEmail(@PathVariable("id") Long id, Model model) {
	           // 1. fetch email from DB using studentId
	    	   System.out.println(id);
	           String email = stuSer.getstudentEmailById(id);
	           System.out.println(email);
	           Optional<Student> stu = stuSer.getstudentBYId(id);
	           
	           if (email == null) {
	               model.addAttribute("error", "Student not found with id: " + id);
	               return "error";  // return error.html template
	           }

	           // 2. send email
	           emailservice.sendEmail(email, "Your email and password for a attendence managment System", "your username\t"+stu.get().getUname()+"\n Your password\t"+stu.get().getPassword());
	           List<Student> li =   stuSer.getAllStudents();
	            model.addAttribute("students", li);
	           // 3. pass message to thymeleaf
	           model.addAttribute("message", "Email successfully sent to " + email);
	           return "send_user_name_and_password_to_student_in_mail";  // thymeleaf template emailSent.html
	       }
	       
	       
	       @PostMapping("/attendance")
	       public String takeAttendance(@RequestParam Map<String, String> attendanceMap,
	                                    RedirectAttributes redirectAttributes,Model model) {

	           LocalDate today = LocalDate.now();

	           // Prevent duplicate entry
	           if (attser.existByDate(today)) {
	               redirectAttributes.addFlashAttribute("alreadyTaken", true);
	               return "redirect:/attendance";
	           }

	           // Save attendance for each student
	           for (Map.Entry<String, String> entry : attendanceMap.entrySet()) {
	               try {
	                   Long studentId = Long.parseLong(entry.getKey());
	                   String status = entry.getValue();
	                   
	               Student student  =  stuSer.getstudentBYId(studentId)
	                		 .orElseThrow(()->new Exception("student not found exception"));
	                   
	                   AttendanceId attendanceId = new AttendanceId(studentId, today);
	                   Attendance sa = new Attendance();
	                   sa.setStudent(student);
	                   sa.setId(attendanceId);
	                   sa.setAttendance(status);

	                   attser.saveAtt(sa);
	                   
	                   List<Attendance> li = student.getAttendanceList();
	                   long tnp =   li.stream().filter(x->x.getAttendance().equals("present")).count();
	                   long tna =  li.stream().filter(x->x.getAttendance().equals("absent")).count();
	                   student.setTotalPresentDays(tnp);
	                   student.setTotalAbsentDays(tna);
	                   stuSer.registerStudent(student);
	                   
	               } catch (Exception e) {
	                   // ignore non-studentId params like _csrf, etc.
	            	     e.printStackTrace();
	               }
	           }
                  List<Student> li = stuSer.getAllStudents();
                  model.addAttribute("students", li);
	              redirectAttributes.addFlashAttribute("attendanceTaken", true);
			   return "attendance";
	   }
	       
	     

	           @PostMapping("/admin/change-password")
	           public String changePassword(@RequestParam("password") String password, Model model) {

	               // TODO: update the password in DB
	        	    Admin admin = adminser.getloggedAdmin();
	        	     admin.setPassword(password);
	        	     adminser.SaveAdmin(admin);
	        	     
	        	     System.out.println(admin);
	               model.addAttribute("successMessage", "Password changed successfully");
	               return "admin_change_password";
	           }
	           
	           
	              @GetMapping("/manage-students/{id}")
	              public  String activeInactivestu(@PathVariable("id")Long id,Model model) {
	        	             try {
								Student stu = stuSer.getstudentBYId(id).orElseThrow(()->new Exception("User not  found"));
								 if(stu.getStatus()== 1) {
									  stu.setStatus(0);

								 }else  {
									 stu.setStatus(1);
								 }
								 boolean bn = stuSer.registerStudent(stu);
								List<Student> li = stuSer.getAllStudents();
								model.addAttribute("students", li);
								 return "manage_student";
							 } catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								 return "error";
							 }
	           }
	              
	              //update student information 
	              @GetMapping("/student/edit")
  	         	public String studentupdateData(Model model) {
  	         		Student student= new Student();
  	         		model.addAttribute("student", student);
  	         		return "student_update_info";
  	         	}
	              
	              // controller for update student information.. save update student
	             @PostMapping("/students/update")
	            public String updatestudent(@ModelAttribute("student")Student Student) {
	            	  stuSer.registerStudent(Student);
	            	return "student_update_info";
	            }
	             
	            
	       }

	       





