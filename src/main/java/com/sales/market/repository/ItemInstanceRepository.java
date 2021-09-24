/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.model.Item;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import org.springframework.data.domain.Example;

import java.time.LocalDate;
import java.util.List;

public interface ItemInstanceRepository extends GenericRepository<ItemInstance> {
    List<ItemInstance> findAllByItemInstanceStatus(ItemInstanceStatus itemInstanceStatus);
    //List<ItemInstance> findAllByItem(Item item);
    List<ItemInstance> findAllByItemAndItemInstanceStatus(Item item, ItemInstanceStatus itemInstanceStatus);
    List<ItemInstance> findAllByExpirationDateIsBefore(LocalDate actualDate);
    List<ItemInstance> findAllByItemAndExpirationDateIsBefore(Item item, LocalDate actualDate);
    boolean existsItemInstanceByIdentifier(String identifier);

}
