package com.order.process.infrastructure.batch;

import com.order.process.domain.model.PurchaseOrder;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.web.servlet.view.MustacheViewResolver;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final MustacheViewResolver mustacheViewResolver;

    public EmailService(JavaMailSender mailSender, MustacheViewResolver mustacheViewResolver) {
        this.mailSender = mailSender;
        this.mustacheViewResolver = mustacheViewResolver;
    }

    public void sendOrderProcessedEmail(PurchaseOrder purchaseOrder) throws MessagingException{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo("customer@example.com");
        helper.setSubject("Order Processed");

        Map<String, Object> model = new HashMap<>();
        model.put("order", purchaseOrder);

        String text = generateEmailContent(model);
        helper.setText(text, true);
        mailSender.send(message);
    }

    private String generateEmailContent(Map<String, Object> model) {
        StringWriter writer = new StringWriter();
        try {
            mustacheViewResolver.setViewNames("orders.mustache");
            mustacheViewResolver.setAttributesMap(model);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate email content", e);
        }
        return writer.toString();
    }
}
