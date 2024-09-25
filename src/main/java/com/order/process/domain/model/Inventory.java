package com.order.process.domain.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.jmolecules.ddd.annotation.AggregateRoot;
import org.jmolecules.ddd.annotation.Identity;

@AggregateRoot
@Entity
public class Inventory {

    @Identity
    @Id
    String productId;
    int quantity;

    public Inventory(String productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
