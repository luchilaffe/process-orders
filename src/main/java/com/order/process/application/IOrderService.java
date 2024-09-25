package com.order.process.application;

import com.order.process.domain.model.PurchaseOrder;
import org.jmolecules.ddd.annotation.Service;

@Service
public interface IOrderService {

    PurchaseOrder createOrder(PurchaseOrder purchaseOrder);
    PurchaseOrder getOrderStatus(String orderId);
    void cancelOrder(String orderId);

    PurchaseOrder processOrder(PurchaseOrder purchaseOrder);
}
