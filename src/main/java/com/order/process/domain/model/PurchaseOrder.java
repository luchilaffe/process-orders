package com.order.process.domain.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

import java.util.Date;
import java.util.List;

@AggregateRoot
@Entity
public class PurchaseOrder {

    @Identity
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Nonnull private String orderId;
    @Nonnull private String customerId;
    @Nonnull private Date orderDate;
    @Nonnull private String status;
    @Nonnull private double totalAmount;

    @Embedded
    @Nonnull private ShippingAddress shippingAddress;

    @OneToMany
    (cascade = CascadeType.ALL)
    private List<ItemOrder> items;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Nonnull
    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(@Nonnull String orderId) {
        this.orderId = orderId;
    }

    @Nonnull
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(@Nonnull String customerId) {
        this.customerId = customerId;
    }

    @Nonnull
    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(@Nonnull Date orderDate) {
        this.orderDate = orderDate;
    }

    @Nonnull
    public String getStatus() {
        return status;
    }

    public void setStatus(@Nonnull String status) {
        this.status = status;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Nonnull
    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(@Nonnull ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    private static final String CANCELED = "CANCELED";

    public void cancelOrder() {
        status = CANCELED;
    }

    public List<ItemOrder> getItems() {
        return items;
    }

    public void setItems(List<ItemOrder> items) {
        this.items = items;
    }
}
