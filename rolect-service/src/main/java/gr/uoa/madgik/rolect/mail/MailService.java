package gr.uoa.madgik.rolect.mail;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

@Service
public class MailService {

    @Autowired
    private Configuration config;

    public void sendMail(Map<String, Object> payload){

        final String from = "noreply.ni4os@gmail.com";
        final String to = "info.ni4os@gmail.com";
        final String username = "noreply.ni4os@gmail.com";
        final String password = "qZ335npT";

        // Get system properties
        Properties props = new Properties();

        // enable authentication
        props.put("mail.smtp.auth",  "true");

        // enable STARTTLS
        props.put("mail.smtp.starttls.enable", "true");

        // Setup mail server
        props.put("mail.smtp.host", "smtp.gmail.com");

        // SSL Port
        props.put("mail.smtp.port", "587");

        // SSL Factory
//        props.put("mail.smtp.socketFactory.class",
//                "javax.net.ssl.SSLSocketFactory");


        // creating Session instance referenced to
        // Authenticator object to pass in
        // Session.getInstance argument
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {

                    //override the getPasswordAuthentication method
                    protected PasswordAuthentication
                    getPasswordAuthentication() {

                        return new PasswordAuthentication(username,
                                password);
                    }
                });
        try {
            // compose the message
            // javax.mail.internet.MimeMessage class is
            // mostly used for abstraction.
            MimeMessage  message = new MimeMessage(session);
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            // construct email template
            Template t =  config.getTemplate("contact-email.ftl");
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,payload);

            // sender
            helper.setFrom(from);

            // receiver
            helper.setTo(to);

            // subject
            helper.setSubject(payload.get("subject").toString());

            // message
            helper.setText(html,true);

            // send
            Transport transport = session.getTransport();
            transport.connect();
            Transport.send(message);
            transport.close();
            System.out.println("Email sent successfully");

        } catch (MessagingException | IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
    }
}
