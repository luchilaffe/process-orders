package com.order.process.infrastructure.batch;

import com.order.process.domain.model.PurchaseOrder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class OrderReader  implements ItemReader<PurchaseOrder> {

    @Override
    public PurchaseOrder read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return null;
    }
}
