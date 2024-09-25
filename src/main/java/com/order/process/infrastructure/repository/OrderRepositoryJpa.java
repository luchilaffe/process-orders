package com.order.process.infrastructure.repository;

import com.order.process.domain.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepositoryJpa extends JpaRepository<PurchaseOrder, String> {

}
