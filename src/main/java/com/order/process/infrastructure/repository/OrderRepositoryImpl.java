package com.order.process.infrastructure.repository;

import com.order.process.domain.model.PurchaseOrder;
import com.order.process.domain.repository.IOrderRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderRepositoryImpl implements IOrderRepository {

    private final OrderRepositoryJpa purchaseOrderJpa;

    public OrderRepositoryImpl (OrderRepositoryJpa purchaseOrderJpa) {
        this.purchaseOrderJpa = purchaseOrderJpa;
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
