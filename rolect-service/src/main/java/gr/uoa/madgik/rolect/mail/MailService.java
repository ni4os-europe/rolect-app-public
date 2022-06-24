package gr.uoa.madgik.rolect.mail;

import com.fasterxml.jackson.databind.ObjectMapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import gr.uoa.madgik.rolect.model.misc.ContactForm;
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

    @Autowired
    private MailConfig mailConfig;

    public void sendMail(ContactForm payload){

        final String from = mailConfig.getUsername();
        final String to = mailConfig.getReceiver();
        final String username = mailConfig.getUsername();
        final String password = mailConfig.getPassword();

        // Get system properties
        Properties props = new Properties();

        // enable authentication
        props.put("mail.smtp.auth",  mailConfig.getMailSmtpAuth().equals("true"));

        // enable STARTTLS
        props.put("mail.smtp.starttls.enable", mailConfig.getMailSmtpStartTls().equals("true"));

        // Setup mail server
        props.put("mail.smtp.host", mailConfig.getHost());

        // SSL Port
        props.put("mail.smtp.port", mailConfig.getPort());

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
//            System.out.println();
//            System.out.println(payload.getEmail());
//            System.out.println(payload.getFullName());
//            System.out.println(payload.getMessage());
//            System.out.println(payload.getSubject());
//            System.out.println();

            config.setClassForTemplateLoading(this.getClass(),"/templates/");
            Template t =  config.getTemplate("contact.ftlh");
            ObjectMapper m = new ObjectMapper();
            String html = FreeMarkerTemplateUtils.processTemplateIntoString(t,m.convertValue(payload, Map.class));


            // sender
            helper.setFrom(from);

            // receiver
            helper.setTo(to);

            // subject
            helper.setSubject(payload.getSubject());

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
