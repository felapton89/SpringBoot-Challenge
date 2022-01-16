package com.alkemychallenge.emailConfig;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class EmailService {

    @Autowired
    private SendGrid sendGrid;

    @Value("${templateId}")
    private String EMAIL_TEMPLATE_ID;
    @Value("${spring.sendgrid.api-key}")
    private String apiKey;


    public String sendEmailWithSendGrid(String email) {

        Email from = new Email("reyes_pabloeduardo@hotmail.com");
        String subject = "Bienvenido a la API de Disney";
        Email to = new Email(email);
        Content content = new Content("text/plain", "¡Bienvenido! Espero disfrutes la API de Disney.");

        Mail mail = new Mail(from, subject, to, content);
        SendGrid sg = new SendGrid(System.getenv(apiKey));
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sendGrid.api(request);

            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return "email was successfully send";
    }

}
