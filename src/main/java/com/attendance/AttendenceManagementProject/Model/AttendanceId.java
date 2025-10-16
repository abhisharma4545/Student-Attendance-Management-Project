package com.attendance.AttendenceManagementProject.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
@Embeddable
public class AttendanceId implements Serializable {

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "system_date")
    private LocalDate systemDate;

    public AttendanceId() {}

    public AttendanceId(Long studentId, LocalDate systemDate) {
        this.studentId = studentId;
        this.systemDate = systemDate;
    }

    // Getters and Setters
    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public LocalDate getSystemDate() {
        return systemDate;
    }

    public void setSystemDate(LocalDate systemDate) {
        this.systemDate = systemDate;
    }

    // equals & hashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AttendanceId)) return false;
        AttendanceId that = (AttendanceId) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(systemDate, that.systemDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, systemDate);
    }
}
