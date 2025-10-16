package com.attendance.AttendenceManagementProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_leave")
public class StudentLeave {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;

    @Column(name = "leave_reasone")
    private String leaveReason;

    @Column(name = "no_of_days")
    private int noOfDays;

    @Column(name = "leave_status")
    private String leaveStatus="pending";

    @Column(name = "leave_apply_date")
    private LocalDate leaveApplyDate;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable =false)
    private Student student;

   
}
