package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;

import java.math.BigDecimal;

public interface ItemInventoryService extends GenericService<ItemInventory> {
    ItemInventory findByItem(Item item);
    ItemInventory updateStockAndTotalPrice(Long id, BigDecimal actualStockQuantity, BigDecimal actualTotalPrice);
    ItemInventory saveItemInventory(Item item, BigDecimal stockQuantity, BigDecimal lower, BigDecimal upper, BigDecimal totalPrice);
    boolean stockReachedLowerBoundThreshold(Long id);
    boolean stockReachedUpperBoundThreshold(Long id);
}