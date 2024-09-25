package com.order.process.application.adapter;

import com.order.process.application.IOrderService;
import com.order.process.domain.model.PurchaseOrder;
import com.order.process.domain.repository.IOrderRepository;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

    private static final String ORDER_NOT_FOUND = "Order Not Found";
    private static final String PROCESSED = "PROCESSED";
    // Counter that increment when an order is created, but not decrement
    private static final String ORDERS_CREATED_COUNTER = "orders.created";
    // Counter that increment when an order is cancelled, but not decrement
    private static final String ORDERS_CANCELED_COUNTER = "orders.canceled";

    private final IOrderRepository orderRepository;
    private final Counter orderCreatedCounter;
    private final Counter orderCanceledCounter;

    public OrderServiceImpl (IOrderRepository orderRepository, MeterRegistry meterRegistry){
        this.orderRepository = orderRepository;
        this.orderCreatedCounter = meterRegistry.counter(ORDERS_CREATED_COUNTER);
        this.orderCanceledCounter = meterRegistry.counter(ORDERS_CANCELED_COUNTER);;
    }

    @Override
    @Transactional
    public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) throws EntityExistsException {
        PurchaseOrder order = orderRepository.create(purchaseOrder);
        orderCreatedCounter.increment();
        return order;
    }

    @Override
    public PurchaseOrder getOrder(String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new EntityNotFoundException(ORDER_NOT_FOUND));
    }

    @Override
    @Transactional
    public void cancelOrder(String orderId) {
        PurchaseOrder purchaseOrder = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException(ORDER_NOT_FOUND)
        );
        purchaseOrder.cancelOrder();
        orderRepository.save(purchaseOrder);
        orderCanceledCounter.increment();
    }

    @Override
    @Transactional
    public PurchaseOrder processOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setStatus(PROCESSED);
        return orderRepository.save(purchaseOrder);
    }

}
