package com.order.process.infrastructure.controller;

import com.order.process.application.IOrderService;
import com.order.process.domain.model.PurchaseOrder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderRestController {

    private final IOrderService orderService;

    public OrderRestController (IOrderService orderService){
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<PurchaseOrder> createOrder(@RequestBody PurchaseOrder purchaseOrder){
        PurchaseOrder newPurchaseOrder = orderService.createOrder(purchaseOrder);
        return new ResponseEntity<>(newPurchaseOrder, HttpStatus.CREATED);
    }

    @GetMapping("{orderId}")
    public ResponseEntity<PurchaseOrder> getOrderStatus(@PathVariable String orderId) {
        PurchaseOrder purchaseOrder = orderService.getOrderStatus(orderId);
        return ResponseEntity.ok(purchaseOrder);
    }

    @DeleteMapping("{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable String orderId) {
        orderService.cancelOrder(orderId);
        return ResponseEntity.noContent().build();
    }

}
