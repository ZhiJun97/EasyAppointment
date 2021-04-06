/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util.email;

import entity.AppointmentEntity;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author zhijun
 */
public class EmailManager {
    
    private final String emailServerName = "smtp.gmail.com";
    private final String mailer = "JavaMailer";
    private String smtpAuthUser;
    private String smtpAuthPassword;

    public EmailManager() {
    }

    public EmailManager(String smtpAuthUser, String smtpAuthPassword) {
        this.smtpAuthUser = smtpAuthUser;
        this.smtpAuthPassword = smtpAuthPassword;
    }
    
    public Boolean emailCheckoutNotification(Date date, String fromEmailAddress, String toEmailAddress) {
        String emailBody = "";
        
        emailBody += "You have an upcoming appointment at " + date.getTime();
        
        try 
        {
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", emailServerName);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");            
            props.put("mail.smtp.debug", "true");            
            javax.mail.Authenticator auth = new SMTPAuthenticator(smtpAuthUser, smtpAuthPassword);
            Session session = Session.getInstance(props, auth);
            session.setDebug(true);            
            Message msg = new MimeMessage(session);
                                    
            if (msg != null)
            {
                msg.setFrom(InternetAddress.parse(fromEmailAddress, false)[0]);
                msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmailAddress, false));
                msg.setSubject("Checkout Completed Successfully!");
                msg.setText(emailBody);
                msg.setHeader("X-Mailer", mailer);
                
                Date timeStamp = new Date();
                msg.setSentDate(timeStamp);
                
                Transport.send(msg);
                
                return true;
            }
            else
            {
                return false;
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
            
            return false;
        }
    }

}
