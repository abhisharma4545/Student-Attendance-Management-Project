package com.attendance.AttendenceManagementProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@Entity
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "stud_name", nullable = false, length = 100)
    private String studName;

    @Column(name = "college_name", nullable = false, length = 150)
    private String collegeName;

    @Column(name = "mobile", nullable = false, length = 15)
    private String mobile;

    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "uname", nullable = false, unique = true, length = 50)
    private String uname;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "status", nullable = false)
    private Integer status;   // 1 = active, 0 = inactive

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Attendance> attendanceList;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentFeedback> feedbackList;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Inquiry> inquiryList;
    
    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentLeave> leaveList;

     @Column(name="TotalPresentDays")
      private Long totalPresentDays;
     
     @Column(name="TotalAbsentDays")
      private Long totalAbsentDays;
     
     @Column(name ="TotalDays" ,updatable = false ,nullable = false)
     private  Long totalDays =365L;
     
}
