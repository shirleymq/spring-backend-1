package com.sales.market.model.purchases;

import com.sales.market.model.ModelBase;

import javax.persistence.Entity;

@Entity
public class Provider extends ModelBase {

    private String name;

    private String code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
