package com.attendance.AttendenceManagementProject.ServiceIml;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EmailServiceimpl {
	
	@Autowired
	public JavaMailSender mailserivce;
	
	public void sendEmail(String to ,String sub,String body) {
		System.out.println(to);
		System.out.println(sub);
		System.out.println(body);

		try {
		   SimpleMailMessage mail = new  SimpleMailMessage();
		   mail.setTo(to);
		   mail.setSubject(sub);
		   mail.setText(body);
		   mailserivce.send(mail);
		}
		catch (Exception e) {
			// TODO: handle exception
			log.error("Exception while sending the mail"+e);
		}
	}

}
