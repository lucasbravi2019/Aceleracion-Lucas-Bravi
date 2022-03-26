package com.bravi.alkemy.email;

import com.bravi.alkemy.exception.EmailException;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailService {

    @Value("${alkemy.email.sender}")
    private String sender;

    @Value("${alkemy.email.enabled}")
    private Boolean enabled;

    public void sendEmail(String destination) throws IOException {
        if (!enabled) throw new EmailException("Emails on register are disabled.");
        if (sender == null) throw new EmailException("Email sender is invalid.");
        Email from = new Email(sender);
        String subject = "Welcome to Disney!";
        Email to = new Email(destination);
        Content content = new Content("text/plain", "Welcome to Disney Api! Enjoy your stay!");
        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            if (response.getStatusCode() != 200 &&
                    response.getStatusCode() != 201 &&
                    response.getStatusCode() != 202
            ) throw new EmailException("There was an error trying to send the email. Please verify your configuration.");
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            throw new EmailException(ex.getMessage());
        }
    }

}
