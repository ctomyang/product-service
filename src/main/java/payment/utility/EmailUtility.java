package payment.utility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;
import payment.exception.EmailException;

@Component
public class EmailUtility {

    public final static String toEmail = "";
    public final static String fromEmail = "";
    private final MailSender mailSender;

    @Autowired
    public EmailUtility(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String text, String to, String from) throws EmailException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setText(text);
        message.setTo(to);
        message.setFrom(from);
        try {
            mailSender.send(message);
        } catch (Exception e) {
            throw new EmailException(e.getMessage());
        }
    }

    public void sendEmail(String text, String to) throws EmailException {
        this.sendEmail(text, to, this.fromEmail);
    }
}
