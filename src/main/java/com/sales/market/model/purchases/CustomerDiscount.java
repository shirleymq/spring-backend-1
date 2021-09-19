package com.sales.market.model.purchases;

import com.sales.market.model.ModelBase;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class CustomerDiscount extends ModelBase {

    @ManyToOne
    private CustomerDiscountRule discountRule;

    @ManyToOne
    private Customer customer;

    private String discountCode;
}
