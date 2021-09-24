package com.sales.market.repository;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;

public interface ItemInventoryRepository extends GenericRepository<ItemInventory> {
    ItemInventory findByItem(Item item);
}