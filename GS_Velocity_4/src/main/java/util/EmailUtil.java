package util;

import test.velocity.Main;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Author: Georgy Gobozov
 * Date: 24.04.13
 */
public class EmailUtil {

    public enum EmailType{
        TEXT, HTML
    }

    public static void send(String to, String subject, String text, EmailType type){

        final String username = "georgy.gobozov@gmail.com";
        final String password = Main.password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            if (type == EmailType.TEXT){
                message.setText(text);
            } else {
                message.setContent(text, "text/html; charset=utf-8");
            }

            Transport.send(message);
            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }




}
