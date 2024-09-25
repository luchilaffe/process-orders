package com.order.process.infrastructure.repository;

import com.order.process.domain.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepositoryJpa  extends JpaRepository<Inventory, String> {

}
