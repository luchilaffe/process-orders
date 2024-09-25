package com.order.process.infrastructure.repository;

import com.order.process.domain.model.PurchaseOrder;
import com.order.process.domain.repository.IOrderRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    private static final String ORDER_EXISTS = "Order already exists";
    private final OrderRepositoryJpa purchaseOrderJpa;

    public OrderRepositoryImpl (OrderRepositoryJpa purchaseOrderJpa) {
        this.purchaseOrderJpa = purchaseOrderJpa;
    }

    @Override
    public PurchaseOrder create(PurchaseOrder purchaseOrder) throws EntityExistsException {
        Optional<PurchaseOrder> order = purchaseOrderJpa.findByOrderId(purchaseOrder.getOrderId());
        if (order.isPresent()) {
            throw new EntityExistsException(ORDER_EXISTS);
        } else {
            return purchaseOrderJpa.save(purchaseOrder);
        }
    }

    @Override
    public PurchaseOrder save(PurchaseOrder purchaseOrder) {
        return purchaseOrderJpa.save(purchaseOrder);
    }

    @Override
    public Optional<PurchaseOrder> findById(String orderId) {
        return purchaseOrderJpa.findById(orderId);
    }

    @Override
    public List<PurchaseOrder> findByStatus(String status) {
        return purchaseOrderJpa.findByStatus(status);
    }

}
