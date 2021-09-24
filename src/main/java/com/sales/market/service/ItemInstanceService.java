/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface ItemInstanceService extends GenericService<ItemInstance> {
    List<ItemInstance> findAllByItemInstanceStatus(ItemInstanceStatus itemInstanceStatus);
    List<ItemInstance> findAllByItemAndItemInstanceStatus(Item item, ItemInstanceStatus itemInstanceStatus);
    List<ItemInstance> findAllByExpirationDateIsBefore(LocalDate actualDate);
    List<ItemInstance> findAllByItemAndExpirationDateIsBefore(Item item, LocalDate actualDate);
    ItemInstance updateStatus(Long itemInstanceId, ItemInstanceStatus itemInstanceStatus);
    ItemInstance saveItemInstance(Item item, String identifier, BigDecimal price, ItemInstanceStatus itemInstanceStatus, LocalDate expirationDate);
    void saveListItemInstance(Item item, String skus, BigDecimal price, LocalDate expirationDate);
}
