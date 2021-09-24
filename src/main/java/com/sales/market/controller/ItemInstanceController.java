/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.dto.ItemInstanceDto;
import com.sales.market.model.Item;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import com.sales.market.service.ItemInstanceService;
import com.sales.market.service.ItemService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/item/instances")
public class ItemInstanceController extends GenericController<ItemInstance, ItemInstanceDto> {
    private ItemInstanceService service;
    private ItemService itemService;

    public ItemInstanceController(ItemInstanceService service, ItemService itemService) {
        this.service = service;
        this.itemService = itemService;
    }

    @Override
    protected ItemInstanceService getService() {
        return service;
    }


    @GetMapping("/available")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    protected List<ItemInstanceDto> getAllAvailable() {
        return toDto(getService().findAllByItemInstanceStatus(ItemInstanceStatus.AVAILABLE));
    }

    @GetMapping("/available/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    protected List<ItemInstanceDto> getAllAvailableByItemId(@PathVariable Long id) {
        Item item = itemService.findById(id);
        return toDto(getService().findAllByItemAndItemInstanceStatus(item,ItemInstanceStatus.AVAILABLE));
    }

    @GetMapping("/sold")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    protected List<ItemInstanceDto> getAllSold() {
        return toDto(getService().findAllByItemInstanceStatus(ItemInstanceStatus.SOLD));
    }

    @GetMapping("/sold/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    protected List<ItemInstanceDto> getAllSoldByItemId(@PathVariable Long id) {
        Item item = itemService.findById(id);
        return toDto(getService().findAllByItemAndItemInstanceStatus(item,ItemInstanceStatus.SOLD));
    }

    @GetMapping("/expired")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    protected List<ItemInstanceDto> getAllScrewed() {
        return toDto(getService().findAllByExpirationDateIsBefore(LocalDate.now()));
    }

    @GetMapping("/expired/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    protected List<ItemInstanceDto> getAllExpiredByItemId(@PathVariable Long id) {
        Item item = itemService.findById(id);
        return toDto(getService().findAllByItemAndExpirationDateIsBefore(item,LocalDate.now()));
    }
}
