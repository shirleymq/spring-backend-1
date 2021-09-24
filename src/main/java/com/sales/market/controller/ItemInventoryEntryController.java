package com.sales.market.controller;

import com.sales.market.dto.ItemInventoryEntryDto;
import com.sales.market.model.ItemInventoryEntry;
import com.sales.market.service.ItemInventoryEntryService;
import com.sales.market.service.GenericService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/item/inventory/entries")
public class ItemInventoryEntryController extends GenericController<ItemInventoryEntry, ItemInventoryEntryDto> {
    private ItemInventoryEntryService service;

    public ItemInventoryEntryController(ItemInventoryEntryService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }

    @PostMapping("/buy/{idItemInventory}/{skus}/{price}/{expirationDate}")
    public ItemInventoryEntryDto buyItems(@PathVariable Long idItemInventory, @PathVariable String skus, @PathVariable BigDecimal price, @PathVariable LocalDate expirationDate){
        return toDto(service.buyItems(idItemInventory, skus, price, expirationDate));
    }

    @PostMapping("/sale/{itemInventoryId}/{quantity}")
    public ItemInventoryEntryDto sellItems(@PathVariable Long itemInventoryId, @PathVariable BigDecimal quantity){
        return toDto(service.sellItems(itemInventoryId, quantity));
    }

    @PostMapping("/remove/{itemInventoryId}")
    public ItemInventoryEntryDto removeItems(@PathVariable Long itemInventoryId){
        return toDto(service.removeItems(itemInventoryId));
    }
}