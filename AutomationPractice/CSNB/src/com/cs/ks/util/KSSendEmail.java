package com.cs.ks.util;

import java.io.File;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MailDateFormat;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import com.cs.ui.util.WebDriverBase;
import com.google.common.base.Charsets;
import com.google.common.io.Files;

public class KSSendEmail extends WebDriverBase
{
	
	/**
	 * 
	 * @author Kevin Meng
	 *
	 */
	
	private static String eHostEStr, eSenderStr, eRecStr, ePasswordStr,deEPasswordStr = null;
	static KSLoadInfo ksLoadInfo = KSLoad.getLoadInfo();
	private static String mpCryptoPassword = "BornToFight";

	@SuppressWarnings("restriction")
	public static void main(String args[]) throws Exception
	{
		StandardPBEStringEncryptor decryptor = new StandardPBEStringEncryptor();
		decryptor.setPassword(mpCryptoPassword);

		eHostEStr = ksLoadInfo.getEmailHost();
		eSenderStr = ksLoadInfo.getEmailSender();
		eRecStr = ksLoadInfo.getEmailReceiver();
		InternetAddress[] eRec = InternetAddress.parse(eRecStr, true);

		ePasswordStr = ksLoadInfo.getEmailPassword();
		deEPasswordStr = decryptor.decrypt(ePasswordStr);

		String attachmentReport = "./Test-Report/TestNGreport/coinsquare-nb-report.html";//file name for attachment 
		@SuppressWarnings("deprecation")
		String content = Files.toString(new File(attachmentReport), Charsets.UTF_8);
		String date = new MailDateFormat().format(new Date());
		String attachmentLog = "./build.log";//file name for attachment
		String attachmentName = "build.log";
		String senderName = "Coinsquare Web Automation";

		//Mail server properties
		Properties prop = System.getProperties();
		prop.put("mail.smtp.starttls.enable", "true");
		prop.put("mail.smtp.host", eHostEStr);
		prop.put("mail.smtp.user", eSenderStr);
		prop.put("mail.smtp.password", deEPasswordStr);
		prop.put("mail.smtp.port", "587");
		prop.put("mail.smtp.auth", "true");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(prop);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(eSenderStr, senderName));
			message.setRecipients(javax.mail.Message.RecipientType.TO,  eRec);
			message.setSubject("Coinsquare Web NB Report - "+date);
			
			// Create the message part 
			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart.setText("Today's (" +date+ ") NB Result: \n");
			
			Multipart multipart = new MimeMultipart();
			// Create the HTML Part
			BodyPart htmlBodyPart = new MimeBodyPart();
			htmlBodyPart.setContent(content , "text/html");
			multipart.addBodyPart(htmlBodyPart);
			
			// Create the attachment part
			BodyPart attachmentBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(attachmentLog);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName(attachmentName);
			multipart.addBodyPart(attachmentBodyPart);
			// Set the Multipart's to be the email's content
			message.setContent(multipart);

			// Send message
			Transport transport = session.getTransport("smtp");
			transport.connect(eHostEStr, eSenderStr, deEPasswordStr);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close(); 
			System.out.println("Email sent successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}