/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import com.sales.market.dto.ItemInstanceDto;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Entity
public class ItemInstance extends ModelBase<ItemInstanceDto> {
    @OneToOne
    private Item item;
    private String identifier;// sku
    private Boolean featured = Boolean.FALSE;
    private BigDecimal price;
    private ItemInstanceStatus itemInstanceStatus;
    private LocalDate expirationDate;

    public ItemInstanceStatus getItemInstanceStatus() {
        return itemInstanceStatus;
    }

    public void setItemInstanceStatus(ItemInstanceStatus itemInstanceStatus) {
        this.itemInstanceStatus = itemInstanceStatus;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    /*@Override
    public ModelBase toDomain(ItemInstanceDto element, ModelMapper mapper) {
        super.toDomain(element, mapper);
        setItem((Item) new Item().toDomain(element.getItemDto(), mapper));
        return this;
    }*/
}
