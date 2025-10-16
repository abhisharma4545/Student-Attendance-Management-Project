package com.attendance.AttendenceManagementProject.Model;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "student_feedback")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fid;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String mobile;

    @Column(nullable = false, length = 1000)
    private String feedback;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="student_id" , nullable =  false)
      private Student student;

    // Constructors
    
}
