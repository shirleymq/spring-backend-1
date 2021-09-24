package com.sales.market.service;

import com.sales.market.model.ItemInventory;
import com.sales.market.model.ItemInventoryEntry;
import com.sales.market.model.MovementType;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface ItemInventoryEntryService extends GenericService<ItemInventoryEntry> {
    ItemInventoryEntry buyItems(Long itemInventoryId, String skus, BigDecimal price, LocalDate expirationDate);
    ItemInventoryEntry sellItems(Long itemInventoryId, BigDecimal quantity);
    ItemInventoryEntry removeItems(Long itemInventoryId);
    ItemInventoryEntry saveItemInventoryEntry(ItemInventory itemInventory, MovementType movementType, BigDecimal quantity, String skus);
}