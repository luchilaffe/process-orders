package com.order.process.infrastructure.repository;

import com.order.process.domain.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepositoryJpa extends JpaRepository<PurchaseOrder, String> {

    List<PurchaseOrder> findByStatus(String status);
    Optional<PurchaseOrder> findByOrderId(String orderId);
}
