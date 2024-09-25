package com.order.process.infrastructure.batch;

import com.order.process.domain.model.PurchaseOrder;
import com.order.process.domain.repository.IOrderRepository;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.List;

@Component
public class OrderReader  implements ItemReader<PurchaseOrder> {

    private static final String PENDING = "PENDING";
    private final IOrderRepository orderRepository;
    private Iterator<PurchaseOrder> orderIterator;

    public OrderReader(IOrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public PurchaseOrder read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        if (orderIterator == null) {
            List<PurchaseOrder> pendingOrders = orderRepository.findByStatus(PENDING);
            orderIterator = pendingOrders.iterator();
        }
        if (orderIterator.hasNext()) {
            return orderIterator.next();
        } else {
            orderIterator = null;
            return null;
        }
    }
}
