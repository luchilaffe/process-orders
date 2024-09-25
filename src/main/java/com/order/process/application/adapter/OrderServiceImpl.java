package com.order.process.application.adapter;

import com.order.process.application.IOrderService;
import com.order.process.domain.model.PurchaseOrder;
import com.order.process.domain.repository.IOrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderServiceImpl implements IOrderService {

    private static final String ORDER_NOT_FOUND = "Order Not Found";
    private static final String PROCESSED = "PROCESSED";
    private final IOrderRepository orderRepository;

    public OrderServiceImpl (IOrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    @Transactional
    public PurchaseOrder createOrder(PurchaseOrder purchaseOrder) {
        return orderRepository.save(purchaseOrder);
    }

    @Override
    @Transactional
    public PurchaseOrder getOrderStatus(String orderId) {
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
    }

    @Override
    public PurchaseOrder processOrder(PurchaseOrder purchaseOrder) {
        purchaseOrder.setStatus(PROCESSED);
        return orderRepository.save(purchaseOrder);
    }

}
