package com.order.process.domain.model;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import org.jmolecules.ddd.annotation.Identity;
import org.jmolecules.ddd.annotation.ValueObject;

@ValueObject
@Entity
public class ItemOrder {

        @Identity
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        @Column(name = "items_order_id")
        private Long itemsOrderId;

        @Nonnull private String productId;
        @Nonnull private String productName;
        @Nonnull private int quantity;
        @Nonnull private double unitPrice;

        public Long getItemsOrderId() {
                return itemsOrderId;
        }

        public void setItemsOrderId(Long itemsOrderId) {
                this.itemsOrderId = itemsOrderId;
        }

        public String getProductId() {
                return productId;
        }

        public void setProductId(String productId) {
                this.productId = productId;
        }

        public String getProductName() {
                return productName;
        }

        public void setProductName(String productName) {
                this.productName = productName;
        }

        public int getQuantity() {
                return quantity;
        }

        public void setQuantity(int quantity) {
                this.quantity = quantity;
        }

        public double getUnitPrice() {
                return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
                this.unitPrice = unitPrice;
        }
}
