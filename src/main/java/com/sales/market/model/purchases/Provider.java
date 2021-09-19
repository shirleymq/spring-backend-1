package com.sales.market.model.purchases;


import com.sales.market.model.ModelBase;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Provider extends ModelBase {

    private String name;

    @Column(nullable = false, updatable = false, insertable = false)
    private String providerCode;
}
