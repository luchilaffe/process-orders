package com.order.process.infrastructure.batch;

import com.order.process.domain.model.PurchaseOrder;
import jakarta.annotation.Nonnull;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessor implements ItemProcessor<PurchaseOrder, PurchaseOrder> {

    @Override
    public PurchaseOrder process(@Nonnull PurchaseOrder purchaseOrder) throws Exception {
        return purchaseOrder;
    }

}
