package com.bibleapp.bibleproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public void sendEmail(String subject, String body) {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("no-reply-bible-service@example.com"); // Set the sender email address
        message.setTo("sam.ashray1@gmail.com"); // Set the recipient email address
        message.setSubject(subject); // Set the email subject
        message.setText(body); // Set the email body
        javaMailSender.send(message); // Send the email
		
		System.out.println("Email sent:\nSubject: " + subject +"\nBody: " + body);
	}

}
