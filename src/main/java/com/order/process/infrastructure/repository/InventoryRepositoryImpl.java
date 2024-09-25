package com.order.process.infrastructure.repository;

import com.order.process.domain.model.Inventory;
import com.order.process.domain.repository.IInventoryRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class InventoryRepositoryImpl implements IInventoryRepository {

    private final InventoryRepositoryJpa inventoryRepositoryJpa;

    public InventoryRepositoryImpl(InventoryRepositoryJpa inventoryRepositoryJpa) {
        this.inventoryRepositoryJpa = inventoryRepositoryJpa;
    }

    @Override
    @Transactional
    public int newQuantity(String productId, int quantity) {
        Optional<Inventory> inventory = inventoryRepositoryJpa.findById(productId);
        if (inventory.isPresent()) {
            inventory.get().setQuantity(quantity);
        } else {
            inventory = Optional.of(new Inventory(productId, quantity));
        }
        return inventoryRepositoryJpa.save(inventory.get()).getQuantity();
    }
}
