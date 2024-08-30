package Utility;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Model.Booking;

public class EmailService {
	public static void sendConfirmation(String to, Booking booking) {
		String from = "meethilpatel92@gmal.com";
		String host = "smtp.gmail.com";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		Session session = Session.getDefaultInstance(properties);
		
		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Booking Confirmation");
			message.setText("Your booking for Slot ID: "+booking.getSlotId() + " has been confirmed.");
			
			Transport.send(message);
			System.out.println("Sent message successfully....");
		}catch(MessagingException ex) {
			ex.printStackTrace();
		}
	}
}
