package com.order.process.domain.repository;

import com.order.process.domain.model.PurchaseOrder;
import jakarta.persistence.EntityExistsException;
import org.jmolecules.ddd.annotation.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderRepository {

    PurchaseOrder create(PurchaseOrder purchaseOrder) throws EntityExistsException;
    PurchaseOrder save(PurchaseOrder purchaseOrder);
    Optional<PurchaseOrder> findById(String orderId);
    List<PurchaseOrder> findByStatus(String status);

}
