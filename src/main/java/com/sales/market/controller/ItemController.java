/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.dto.ItemDto;
import com.sales.market.model.Item;
import com.sales.market.service.GenericService;
import com.sales.market.service.ItemService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/items")
public class ItemController extends GenericController<Item, ItemDto> {
    private ItemService service;

    public ItemController(ItemService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
