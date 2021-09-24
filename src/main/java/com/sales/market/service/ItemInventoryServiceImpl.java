package com.sales.market.service;

import com.sales.market.model.Item;
import com.sales.market.model.ItemInventory;
import com.sales.market.repository.ItemInventoryRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ItemInventoryServiceImpl extends GenericServiceImpl<ItemInventory> implements ItemInventoryService {
    private final ItemInventoryRepository repository;

    public ItemInventoryServiceImpl(ItemInventoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<ItemInventory> getRepository() {
        return repository;
    }

    public ItemInventory findByItem(Item item){
        return repository.findByItem(item);
    }

    public ItemInventory updateStockAndTotalPrice(Long id, BigDecimal actualStockQuantity, BigDecimal actualTotalPrice){
        ItemInventory itemInventory = findById(id);
        itemInventory.setStockQuantity(actualStockQuantity);
        itemInventory.setTotalPrice(actualTotalPrice);
        return repository.save(itemInventory);
    }

    public ItemInventory saveItemInventory(Item item, BigDecimal stockQuantity, BigDecimal lower, BigDecimal upper, BigDecimal totalPrice){
       ItemInventory itemInventory = new ItemInventory();
       itemInventory.setItem(item);
       itemInventory.setStockQuantity(stockQuantity);
       itemInventory.setLowerBoundThreshold(lower);
       itemInventory.setUpperBoundThreshold(upper);
       itemInventory.setTotalPrice(totalPrice);
       return repository.save(itemInventory);
    }

    public boolean stockReachedLowerBoundThreshold(Long id){
        boolean res = false;
        ItemInventory itemInventory = findById(id);
        BigDecimal stockQuantity = itemInventory.getStockQuantity();
        BigDecimal lowerBoundThreshold = itemInventory.getLowerBoundThreshold();
        if(stockQuantity.compareTo(lowerBoundThreshold)<=0){
            res = true;
        }
        return res;
    }

    public boolean stockReachedUpperBoundThreshold(Long id){
        boolean res = false;
        ItemInventory itemInventory = findById(id);
        BigDecimal stockQuantity = itemInventory.getStockQuantity();
        BigDecimal upperBoundThreshold = itemInventory.getUpperBoundThreshold();
        if(stockQuantity.compareTo(upperBoundThreshold)>=0){
            res = true;
        }
        return res;
    }
}