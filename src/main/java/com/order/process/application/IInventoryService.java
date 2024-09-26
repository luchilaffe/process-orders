package com.order.process.application;

import org.jmolecules.ddd.annotation.Service;

@Service
public interface IInventoryService {

    int updateInventory(String productId, int quantity);

}
