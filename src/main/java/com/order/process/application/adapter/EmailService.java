package com.order.process.application.adapter;

import com.order.process.application.IEmailService;
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
public class EmailService implements IEmailService {

    private static final String MUSTACHE_FILE = "orderProcessed.mustache";
    private static final String CONTENT_EXCEPTION = "Failed to generate email content";

    private final JavaMailSender mailSender;
    private final MustacheViewResolver mustacheViewResolver;

    public EmailService(JavaMailSender mailSender, MustacheViewResolver mustacheViewResolver) {
        this.mailSender = mailSender;
        this.mustacheViewResolver = mustacheViewResolver;
    }

    @Override
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
            mustacheViewResolver.setViewNames(MUSTACHE_FILE);
            mustacheViewResolver.setAttributesMap(model);
        } catch (Exception e) {
            throw new RuntimeException(CONTENT_EXCEPTION, e);
        }
        return writer.toString();
    }
}
