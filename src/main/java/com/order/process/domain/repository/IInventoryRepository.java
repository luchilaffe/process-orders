package com.order.process.domain.repository;

import org.jmolecules.ddd.annotation.Repository;

@Repository
public interface IInventoryRepository {

    int newQuantity(String productId, int quantity);

}
