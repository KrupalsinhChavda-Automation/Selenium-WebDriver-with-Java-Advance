package utilities;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailService {

    public static void main(String[] args) {
        // Recipient's email ID needs to be mentioned.
        String to = "krupalchavda3499@gmail.com";

        // Sender's email ID needs to be mentioned
        String from = "kbckiller3499@gmail.com";
        final String username = "kbckiller3499@gmail.com"; // your Gmail username
        final String password = "bmxgarmndkjdpdqx"; // your Gmail password

        // Assuming you are sending email through Gmail's SMTP server
        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        // Get the Session object.
        Session session = Session.getInstance(props,
          new javax.mail.Authenticator() {
             protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
             }
          });

        try {
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Test Email from Java");

            // Now set the actual message
            message.setText("Hello, this is a test email sent from Java code!");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully....");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
