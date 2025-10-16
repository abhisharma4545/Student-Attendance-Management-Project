package com.attendance.AttendenceManagementProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "inquiry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_id")
    private Long inquiryId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "mobile", nullable = false, length = 15)
    private String mobile;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "message", columnDefinition = "TEXT")
    private String message;
    
    @Column(name="ActionTaken" ,nullable= false)
    private String actionTaken="We will check and solve or update your issue";

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="student_id",nullable = false)
    private Student student;

    // 
   
}

