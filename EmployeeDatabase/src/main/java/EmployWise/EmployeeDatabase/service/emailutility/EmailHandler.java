package EmployWise.EmployeeDatabase.service.emailutility;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailHandler {
    private static final String EMAIL_USERNAME ="p22961843@gmail.com";
    private static final String EMAIL_PASSWORD ="mmpn qsis bgdy ooho";

    public static boolean sendEmailToManager(String managerEmail, String employeeName, String phoneNumber, String emailId) {
        String subject = "New Employee Addition";
        String body = String.format("%s will now work under you. Mobile number is %s and email is %s", employeeName, phoneNumber, emailId);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(managerEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
            System.out.println("Email sent successfully to " + managerEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }
}
