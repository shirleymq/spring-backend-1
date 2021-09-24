package com.sales.market.service;

import com.sales.market.model.*;
import com.sales.market.repository.ItemInventoryEntryRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ItemInventoryEntryServiceImpl extends GenericServiceImpl<ItemInventoryEntry> implements ItemInventoryEntryService {
    private final ItemInventoryEntryRepository repository;
    private final ItemInventoryService itemInventoryService;
    private final ItemInstanceService itemInstanceService;
    private final EmailService emailService;
    @Value("${warehouse.manager}")
    private String warehouseManager;


    public ItemInventoryEntryServiceImpl(ItemInventoryEntryRepository repository, ItemInventoryService itemInventoryService, ItemInstanceService itemInstanceService, EmailService emailService) {
        this.repository = repository;
        this.itemInventoryService = itemInventoryService;
        this.itemInstanceService = itemInstanceService;
        this.emailService = emailService;
    }

    @Override
    protected GenericRepository<ItemInventoryEntry> getRepository() {
        return repository;
    }

    public ItemInventoryEntry buyItems(Long itemInventoryId, String skus, BigDecimal price, LocalDate expirationDate){
        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        ItemInventory itemInventory = itemInventoryService.findById(itemInventoryId);
        if(itemInventory!=null){
            Item item = itemInventory.getItem();
            //crear las instancias de los item comprados
            itemInstanceService.saveListItemInstance(item, skus, price, expirationDate);
            String[] skusList = skus.split(" ");
            BigDecimal quantityBuy = new BigDecimal(skusList.length);
            itemInventoryEntry = saveItemInventoryEntry(itemInventory, MovementType.BUY, quantityBuy,skus);
            //actualizar itemInventory
            BigDecimal actualStock = itemInventory.getStockQuantity().add(quantityBuy);
            BigDecimal totalPriceBuy = price.multiply(quantityBuy);
            BigDecimal actualTotalPrice = itemInventory.getTotalPrice().add(totalPriceBuy);
            itemInventoryService.updateStockAndTotalPrice(itemInventoryId, actualStock, actualTotalPrice);
            //revisar si el stock alcanzo el limite del umbral maximo
            boolean sendEmail = itemInventoryService.stockReachedUpperBoundThreshold(itemInventoryId);
            if (sendEmail == true){
                String text = "Estan comprando mas de lo que deberian del producto "+item.getName()+", no se debe hacer eso.";
                senEmailAlert(warehouseManager,"Alerta de almacen", text);
            }
        }
        return itemInventoryEntry;
    }

    public ItemInventoryEntry sellItems(Long itemInventoryId, BigDecimal quantity){
        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        ItemInventory itemInventory = itemInventoryService.findById(itemInventoryId);
        if(itemInventory!=null){
            Item item = itemInventory.getItem();
            BigDecimal stockQuantity = itemInventory.getStockQuantity();
            if (stockQuantity.compareTo(quantity)>=0){
                //lista de los posibles items a vender
                List<ItemInstance> itemInstanceList = itemInstanceService.findAllByItemAndItemInstanceStatus(item, ItemInstanceStatus.AVAILABLE);
                String skus = "";
                BigDecimal totalPriceSold = new BigDecimal(0);
                int i=0;
                while (i < quantity.intValue()){
                    ItemInstance itemInstance = itemInstanceList.get(i);
                    itemInstanceService.updateStatus(itemInstance.getId(),ItemInstanceStatus.SOLD);
                    skus = skus + " " + itemInstance.getIdentifier();
                    totalPriceSold = totalPriceSold.add(itemInstance.getPrice());
                    i++;
                }
                itemInventoryEntry = saveItemInventoryEntry(itemInventory,MovementType.SALE,quantity,skus);
                //actualizar itemInventory
                BigDecimal actualStock = itemInventory.getStockQuantity().subtract(quantity);
                BigDecimal actualTotalPrice = itemInventory.getTotalPrice().subtract(totalPriceSold);
                itemInventoryService.updateStockAndTotalPrice(itemInventoryId,actualStock,actualTotalPrice);
                //revisar si el stock alcanzo el limite del umbral minimo
                boolean sendEmail = itemInventoryService.stockReachedLowerBoundThreshold(itemInventoryId);
                if (sendEmail == true){
                    String text = "El producto "+item.getName()+" ha llegado a su umbral minimo, tienes que comprar mas.";
                    senEmailAlert(warehouseManager,"Alerta de almacen", text);
                }
            }
            else{
                System.out.println("No se puede vender, usted esta solicitando mas de lo que hay");
            }
        }
        return itemInventoryEntry;
    }

    public ItemInventoryEntry removeItems(Long itemInventoryId) {
        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        ItemInventory itemInventory = itemInventoryService.findById(itemInventoryId);
        if (itemInventory != null) {
            Item item = itemInventory.getItem();
            //lista de items para remover
            List<ItemInstance> itemInstanceList = itemInstanceService.findAllByItemAndExpirationDateIsBefore(item, LocalDate.now());
            if (itemInstanceList.size()>0){
                String skus = "";
                BigDecimal totalPriceRemoved = new BigDecimal(0);
                for (ItemInstance itemInstance:itemInstanceList) {
                    itemInstanceService.updateStatus(itemInstance.getId(),ItemInstanceStatus.EXPIRED);
                    skus = skus + " " + itemInstance.getIdentifier();
                    totalPriceRemoved = totalPriceRemoved.add(itemInstance.getPrice());
                }
                itemInventoryEntry = saveItemInventoryEntry(itemInventory,MovementType.REMOVED,new BigDecimal(itemInstanceList.size()),skus);
                //actualizar itemInventory
                BigDecimal numberItemsRemoved = new BigDecimal(itemInstanceList.size());
                BigDecimal actualStock = itemInventory.getStockQuantity().subtract(numberItemsRemoved);
                BigDecimal actualTotalPrice = itemInventory.getTotalPrice().subtract(totalPriceRemoved);
                itemInventoryService.updateStockAndTotalPrice(itemInventoryId,actualStock,actualTotalPrice);
                //revisar si el stock alcanzo el limite del umbral minimo
                boolean sendEmail = itemInventoryService.stockReachedLowerBoundThreshold(itemInventoryId);
                if (sendEmail == true){
                    String text = "El producto "+item.getName()+" ha llegado a su umbral minimo, tienes que comprar mas.";
                    senEmailAlert(warehouseManager,"Alerta de almacen", text);
                }
            }
        }
        return itemInventoryEntry;
    }

    public ItemInventoryEntry saveItemInventoryEntry(ItemInventory itemInventory,MovementType movementType, BigDecimal quantity, String skus){
        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        itemInventoryEntry.setItemInventory(itemInventory);
        itemInventoryEntry.setMovementType(movementType);
        itemInventoryEntry.setQuantity(quantity);
        itemInventoryEntry.setItemInstanceSkus(skus);
        return repository.save(itemInventoryEntry);
    }

    public void senEmailAlert(String to, String subject, String text){
        emailService.sendSimpleMessage(to, subject, text);
    }
}