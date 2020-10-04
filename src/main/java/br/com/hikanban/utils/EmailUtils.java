package br.com.hikanban.utils;



import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Map;
import java.util.Properties;

@Component
public class EmailUtils {

    @Value("${mail.host}")
    private String host;

    @Value("${mail.port}")
    private Integer port;

    @Value("${mail.user}")
    private String user;

    @Value("${mail.password}")
    private String password;

    @Value("${mail.from}")
    private String from;

    /**
     * Sends an Email
     * The email's body is built by replacing variables from "template" using values defined in "values".
     * Variables in "template" should be encapsulated in two sets of {}. Ex: {{username}}
     * Values in "values" should have the variable name defined in the template as the key.
     *
     * If the email does not need variables "values" should be null.
     *
     * @param toEmail destination email address
     * @param subject email's subject
     * @param template email's template
     * @param values a map of values
     */
    public void SendEmail(String toEmail, String subject, String template, Map<String, String> values) {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(user);
        mailSender.setPassword(password);

        Properties mailProp = mailSender.getJavaMailProperties();
        mailProp.put("mail.transport.protocol", "smtp");
        mailProp.put("mail.smtp.auth", "true");
        mailProp.put("mail.smtp.starttls.enable", "true");

        try {
            String body;
            if(values != null && !values.isEmpty()) {
                body = HtmlUtils.BuildBody(template, values);
            } else {
                body = template;
            }

            MimeMessage message = mailSender.createMimeMessage();
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
