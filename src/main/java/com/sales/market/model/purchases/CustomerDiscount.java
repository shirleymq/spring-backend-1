package com.sales.market.model.purchases;

import com.sales.market.model.ModelBase;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


@Entity

public class CustomerDiscount extends ModelBase {

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomerDiscountRule discountRule;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

}
