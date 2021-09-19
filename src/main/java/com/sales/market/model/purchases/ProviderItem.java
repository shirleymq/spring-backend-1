/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model.purchases;

import com.sales.market.model.Item;

public class ProviderItem {

    private Provider provider;
    //codigo con el que el proveedor conoce al item
    private String providerItemCode;

    //facilitara los queries
    private String providerCode;

    private Item item;

    //facilitara los queries
    private String itemCode;

    private MeasureUnit measureUnit;

    private Double price;

    public void setProvider(Provider provider) {
        this.provider = provider;
        this.providerCode = provider.getCode();
    }

    public void setItem(Item item) {
        this.item = item;
        this.itemCode = item.getCode();
    }
}
