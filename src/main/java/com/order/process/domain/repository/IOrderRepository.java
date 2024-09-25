package com.order.process.domain.repository;

import com.order.process.domain.model.PurchaseOrder;
import org.jmolecules.ddd.annotation.Repository;

import java.util.Optional;

@Repository
public interface IOrderRepository {

    PurchaseOrder save(PurchaseOrder purchaseOrder);
    Optional<PurchaseOrder> findById(String orderId);

}
