package com.sales.market.model.purchases;

import com.sales.market.model.ModelBase;

import javax.persistence.Entity;

@Entity
public class MeasureUnit extends ModelBase {

    private String measureUnitCode;

    private String name;

    private String description;

}
