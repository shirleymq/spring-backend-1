/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market;

import com.sales.market.model.*;
import com.sales.market.repository.BuyRepository;
import com.sales.market.service.*;
import io.micrometer.core.instrument.util.IOUtils;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private final BuyRepository buyRepository;
    private final CategoryService categoryService;
    private final SubCategoryService subCategoryService;
    private final ItemService itemService;
    private final ItemInstanceService itemInstanceService;
    private final ItemInventoryService itemInventoryService;
    private final ItemInventoryEntryService itemInventoryEntryService;

    SubCategory beverageSubCat = null;
    SubCategory beverageSubCat2 = null;

    // injeccion evita hacer instancia   = new Clase();
    // bean pueden tener muchos campos y otros beans asociados

    public DevelopmentBootstrap(BuyRepository buyRepository, CategoryService categoryService,
                                SubCategoryService subCategoryService, ItemService itemService, ItemInstanceService itemInstanceService, ItemInventoryService itemInventoryService, ItemInventoryEntryService itemInventoryEntryService) {
        this.buyRepository = buyRepository;
        this.categoryService = categoryService;
        this.subCategoryService = subCategoryService;
        this.itemService = itemService;
        this.itemInstanceService = itemInstanceService;
        this.itemInventoryService = itemInventoryService;
        this.itemInventoryEntryService = itemInventoryEntryService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("evento de spring");
        persistBuy(BigDecimal.TEN);
        persistBuy(BigDecimal.ONE);
        persistCategoriesAndSubCategories();
        Item maltinItem = persistItems(beverageSubCat);
        Item wafferItem = persistItems2(beverageSubCat2);
        persistItemInstances(maltinItem);
        persistItemInstances2(wafferItem);
        ItemInventory itemInventory = persistItemInventory(maltinItem);
        ItemInventory itemInventory2 = persistItemInventory2(wafferItem);
        //persistItemInventoryEntry(itemInventory);
    }

    private void persistItemInstances(Item maltinItem) {
        ItemInstance maltinItem1 = createItem(maltinItem, "SKU-77721106006158", new BigDecimal(5), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-12-25"));
        ItemInstance maltinItem2 = createItem(maltinItem, "SKU-77721106006159", new BigDecimal(5), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-10-10"));
        ItemInstance maltinItem3 = createItem(maltinItem, "SKU-77721106006160", new BigDecimal(5), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-10-10"));
        ItemInstance maltinItem4 = createItem(maltinItem, "SKU-77721106006161", new BigDecimal(5), ItemInstanceStatus.SOLD, LocalDate.parse("2021-11-15"));
        ItemInstance maltinItem5 = createItem(maltinItem, "SKU-77721106006162", new BigDecimal(5), ItemInstanceStatus.EXPIRED, LocalDate.parse("2021-09-21"));
        itemInstanceService.save(maltinItem1);
        itemInstanceService.save(maltinItem2);
        itemInstanceService.save(maltinItem3);
        itemInstanceService.save(maltinItem4);
        itemInstanceService.save(maltinItem5);
    }

    private void persistItemInstances2(Item maltinItem) {
        ItemInstance maltinItem1 = createItem(maltinItem, "SKU-87721106006158", new BigDecimal(3), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-12-25"));
        ItemInstance maltinItem2 = createItem(maltinItem, "SKU-87721106006159", new BigDecimal(3), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-10-10"));
        ItemInstance maltinItem3 = createItem(maltinItem, "SKU-87721106006160", new BigDecimal(3), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-10-10"));
        ItemInstance maltinItem4 = createItem(maltinItem, "SKU-87721106006161", new BigDecimal(3), ItemInstanceStatus.SOLD, LocalDate.parse("2021-11-17"));
        ItemInstance maltinItem5 = createItem(maltinItem, "SKU-87721106006161", new BigDecimal(3), ItemInstanceStatus.EXPIRED, LocalDate.parse("2021-09-21"));

        itemInstanceService.save(maltinItem1);
        itemInstanceService.save(maltinItem2);
        itemInstanceService.save(maltinItem3);
        itemInstanceService.save(maltinItem4);
        itemInstanceService.save(maltinItem5);
    }

    private ItemInstance createItem(Item maltinItem, String sku, BigDecimal price, ItemInstanceStatus instanceStatus, LocalDate expirationDate) {
        ItemInstance itemInstance = new ItemInstance();
        itemInstance.setItem(maltinItem);
        itemInstance.setFeatured(true);
        itemInstance.setPrice(price);
        itemInstance.setIdentifier(sku);
        itemInstance.setItemInstanceStatus(instanceStatus);
        itemInstance.setExpirationDate(expirationDate);
        return itemInstance;
    }

    private Item persistItems(SubCategory subCategory) {
        Item item = new Item();
        item.setCode("B-MALTIN");
        item.setName("MALTIN");
        item.setSubCategory(subCategory);
        return itemService.save(item);
    }

    private Item persistItems2(SubCategory subCategory) {
        Item item = new Item();
        item.setCode("C-WAFFER");
        item.setName("WAFFER");
        item.setSubCategory(subCategory);
        return itemService.save(item);
    }

    private String getResourceAsString(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private InputStream getResourceAsStream(String resourceName) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourceName)) {
            return inputStream;
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void persistCategoriesAndSubCategories() {
        Category category = persistCategory();
        Category category2 = persistCategory2();
        //persistSubCategory("SUBCAT1-NAME", "SUBCAT1-CODE", category);
        beverageSubCat = persistSubCategory("BEVERAGE", "BEVERAGE-CODE", category);
        beverageSubCat2 = persistSubCategory("COOKIE", "COOKIE-CODE",category2);
    }

    private Category persistCategory() {
        Category category = new Category();
        category.setName("CAT1-NAME");
        category.setCode("CAT1-CODE");
        return categoryService.save(category);
    }
    private Category persistCategory2() {
        Category category = new Category();
        category.setName("CAT2-NAME");
        category.setCode("CAT2-CODE");
        return categoryService.save(category);
    }

    private SubCategory persistSubCategory(String name, String code, Category category) {
        SubCategory subCategory = new SubCategory();
        subCategory.setName(name);
        subCategory.setCode(code);
        subCategory.setCategory(category);
        return subCategoryService.save(subCategory);
    }

    private void persistBuy(BigDecimal value) {
        Buy buy = new Buy();
        buy.setValue(value);
        buyRepository.save(buy);
    }

    private ItemInventory persistItemInventory(Item item){
        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setItem(item);
        itemInventory.setStockQuantity(new BigDecimal(3));
        itemInventory.setLowerBoundThreshold(new BigDecimal(2));
        itemInventory.setUpperBoundThreshold(new BigDecimal(5));
        itemInventory.setTotalPrice(new BigDecimal(15));
        return itemInventoryService.save(itemInventory);
    }
    private ItemInventory persistItemInventory2(Item item){
        ItemInventory itemInventory = new ItemInventory();
        itemInventory.setItem(item);
        itemInventory.setStockQuantity(new BigDecimal(3));
        itemInventory.setLowerBoundThreshold(new BigDecimal(2));
        itemInventory.setUpperBoundThreshold(new BigDecimal(5));
        itemInventory.setTotalPrice(new BigDecimal(9));
        return itemInventoryService.save(itemInventory);
    }

    private void persistItemInventoryEntry(ItemInventory itemInventory){
        buyItemInstances(itemInventory.getItem()); //162, 163, 164, 165
        ItemInventoryEntry itemInventoryEntry1 = createItemInventoryEntry(itemInventory,MovementType.BUY, new BigDecimal(4), "SKU-77721106006162, SKU-77721106006163, SKU-77721106006164, SKU-77721106006165" );
        itemInventoryEntryService.save(itemInventoryEntry1);
        //ItemInventoryEntry itemInventoryEntry2 = createItemInventoryEntry(itemInventory,MovementType.SALE, new BigDecimal(2), "SKU-77721106006162, SKU-77721106006163" );
    }

    private ItemInventoryEntry createItemInventoryEntry(ItemInventory itemInventory, MovementType movementType, BigDecimal quantity, String skues){
        ItemInventoryEntry itemInventoryEntry = new ItemInventoryEntry();
        itemInventoryEntry.setItemInventory(itemInventory);
        itemInventoryEntry.setMovementType(movementType);
        itemInventoryEntry.setQuantity(quantity);
        itemInventoryEntry.setItemInstanceSkus(skues);
        return itemInventoryEntryService.save(itemInventoryEntry);
    }

    private void buyItemInstances(Item maltinItem) {
        ItemInstance maltinItem1 = createItem(maltinItem, "SKU-77721106006162", new BigDecimal(5), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-08-20"));
        ItemInstance maltinItem2 = createItem(maltinItem, "SKU-77721106006163", new BigDecimal(5), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-09-21"));
        ItemInstance maltinItem3 = createItem(maltinItem, "SKU-77721106006164", new BigDecimal(5), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-09-23"));
        ItemInstance maltinItem4 = createItem(maltinItem, "SKU-77721106006165", new BigDecimal(5), ItemInstanceStatus.AVAILABLE, LocalDate.parse("2021-09-23"));
        itemInstanceService.save(maltinItem1);
        itemInstanceService.save(maltinItem2);
        itemInstanceService.save(maltinItem3);
        itemInstanceService.save(maltinItem4);
    }
}
