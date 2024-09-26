package com.order.process.application.adapter;

import com.order.process.application.IInventoryService;
import com.order.process.domain.repository.IInventoryRepository;
import org.springframework.stereotype.Service;

@Service
public class InventoryServiceImpl implements IInventoryService {

    private final IInventoryRepository inventoryRepository;

    public InventoryServiceImpl(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Override
    public int updateInventory(String productId, int quantity) {
        return inventoryRepository.newQuantity(productId, quantity);
    }
}
