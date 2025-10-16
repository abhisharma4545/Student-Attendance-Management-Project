package com.attendance.AttendenceManagementProject.Model;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
//@Table(name="admin")
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "admin_name")
	private String name;
	private String email;
	@Column(name="uname" , unique = true)
	private String uname;
	private String password;
	
	  
	
	
	
	
	
	}
