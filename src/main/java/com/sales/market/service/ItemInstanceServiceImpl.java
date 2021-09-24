/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInstance;
import com.sales.market.model.ItemInstanceStatus;
import com.sales.market.model.ItemInventory;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService {
    private final ItemInstanceRepository repository;
    private final ItemService itemService;
    private final ItemInventoryService itemInventoryService;

    public ItemInstanceServiceImpl(ItemInstanceRepository repository, ItemService itemService, ItemInventoryService itemInventoryService) {
        this.repository = repository;
        this.itemService = itemService;
        this.itemInventoryService = itemInventoryService;
    }

    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return repository;
    }

    @Override
    public ItemInstance bunchSave(ItemInstance itemInstance) {
        if (itemInstance.getItem() != null) {
            itemService.save(itemInstance.getItem());
        }
        return super.bunchSave(itemInstance);
    }

    public List<ItemInstance> findAllByItemInstanceStatus(ItemInstanceStatus itemInstanceStatus){
        List<ItemInstance> itemInstanceList = repository.findAllByItemInstanceStatus(itemInstanceStatus);
        return itemInstanceList;
    }

    public List<ItemInstance> findAllByItemAndItemInstanceStatus(Item item, ItemInstanceStatus itemInstanceStatus){
        List<ItemInstance> itemInstanceList = repository.findAllByItemAndItemInstanceStatus(item, itemInstanceStatus);
        return itemInstanceList;
    }

    public boolean existsItemInstanceByIdentifier(String identifier){
        return repository.existsItemInstanceByIdentifier(identifier);
    }

    public List<ItemInstance> findAllByExpirationDateIsBefore(LocalDate actualDate){
        return repository.findAllByExpirationDateIsBefore(actualDate);
    }

    public List<ItemInstance> findAllByItemAndExpirationDateIsBefore(Item item, LocalDate actualDate){
        return repository.findAllByItemAndExpirationDateIsBefore(item, actualDate);
    }

    public ItemInstance updateStatus(Long itemInstanceId, ItemInstanceStatus itemInstanceStatus){
        ItemInstance itemInstance = repository.getById(itemInstanceId);
        if(itemInstance!=null) {
            itemInstance.setItemInstanceStatus(itemInstanceStatus);
            repository.save(itemInstance);
        }
        /*ItemInventory itemInventory = itemInventoryService.findByItem(item);
        if(itemInstanceStatus.equals(ItemInstanceStatus.AVAILABLE)){
            itemInstance.setItemInstanceStatus(itemInstanceStatus);
            itemInventory.setStockQuantity(itemInventory.getStockQuantity().add(new BigDecimal(1)));
        }
        else{
            if (itemInstanceStatus.equals(ItemInstanceStatus.SOLD)){
                itemInstance.setItemInstanceStatus(itemInstanceStatus);
                itemInventory.setStockQuantity(itemInventory.getStockQuantity().subtract(new BigDecimal(1)));
            }
            else {
                itemInstance.setItemInstanceStatus(itemInstanceStatus);
                itemInventory.setStockQuantity(itemInventory.getStockQuantity().subtract(new BigDecimal(1)));
            }
        }*/
        return itemInstance;
    }

    public ItemInstance saveItemInstance(Item item, String identifier, BigDecimal price, ItemInstanceStatus itemInstanceStatus, LocalDate expirationDate){
        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setItem(item);
        itemInstance.setIdentifier(identifier);
        itemInstance.setPrice(price);
        itemInstance.setItemInstanceStatus(itemInstanceStatus);
        itemInstance.setExpirationDate(expirationDate);
        return repository.save(itemInstance);
    }

    public void saveListItemInstance(Item item, String skus, BigDecimal price, LocalDate expirationDate){
        String[] skusList = skus.split(" ");
        for (String sku: skusList) {
            if (existsItemInstanceByIdentifier(sku) == false) {
                ItemInstance itemInstance = saveItemInstance(item, sku, price, ItemInstanceStatus.AVAILABLE, expirationDate);
            }
        }
    }
    @Override
    public ItemInstance save(ItemInstance itemInstance){
        ItemInstance itemInstance1 = new ItemInstance();
        String sku = itemInstance.getIdentifier();
        if (existsItemInstanceByIdentifier(sku)==false){
            validateSave(itemInstance);
            itemInstance1 = getRepository().save(itemInstance);
            return findById(itemInstance1.getId());
        }
        return itemInstance1;
    }
}
