package com.order.process.infrastructure.batch;

import com.order.process.application.IEmailService;
import com.order.process.domain.model.PurchaseOrder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
public class OrderWriter implements ItemWriter<PurchaseOrder> {

    private final IEmailService emailService;

    public OrderWriter(IEmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void write(Chunk<? extends PurchaseOrder> orders) throws Exception {
        for (PurchaseOrder purchaseOrder : orders) {
            emailService.sendOrderProcessedEmail(purchaseOrder);
        }
    }

}
