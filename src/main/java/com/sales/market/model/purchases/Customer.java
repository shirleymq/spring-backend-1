package com.sales.market.model.purchases;


import com.sales.market.model.ModelBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Customer extends ModelBase {

    private String number;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date firstPurchase;

    @Temporal(value = TemporalType.TIMESTAMP)
    private Date lastPurchase;

    //totalarticulosadquiridos
    private Integer totalPurchasedProducts;

    //totalimporteadquirido
    private BigDecimal totalPurchasedAmount;

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY)
    private List<CustomerDiscount> discounts = new ArrayList<CustomerDiscount>(0);

}
