package com.order.process.application;

import com.order.process.domain.model.PurchaseOrder;
import jakarta.mail.MessagingException;
import org.jmolecules.ddd.annotation.Service;

@Service
public interface IEmailService {

    void sendOrderProcessedEmail(PurchaseOrder purchaseOrder) throws MessagingException;

}
