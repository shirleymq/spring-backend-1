/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.dto.ItemInstanceDto;
import com.sales.market.model.ItemInstance;
import com.sales.market.service.ItemInstanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/iteminstances")
public class ItemInstanceController extends GenericController<ItemInstance, ItemInstanceDto> {
    private ItemInstanceService service;

    public ItemInstanceController(ItemInstanceService service) {
        this.service = service;
    }

    @PostMapping("bunch")
    protected ItemInstanceDto save(@RequestBody ItemInstanceDto element, @RequestParam(name = "bunchSave", required =
            false, defaultValue = "false") Boolean bunchSave) {
        return toDto((ItemInstance) getService().bunchSave(toModel(element), bunchSave));
    }

    @Override
    protected ItemInstanceService getService() {
        return service;
    }
}
