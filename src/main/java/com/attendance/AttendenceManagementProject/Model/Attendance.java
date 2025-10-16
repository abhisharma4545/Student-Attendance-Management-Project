package com.attendance.AttendenceManagementProject.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "student_attendance")
@AllArgsConstructor
@NoArgsConstructor
public class Attendance {

    @EmbeddedId
    private AttendanceId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("studentId") // Maps to AttendanceId.studentId
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Column(nullable = false, length = 20)
    private String attendance; // Present/Absent
}
