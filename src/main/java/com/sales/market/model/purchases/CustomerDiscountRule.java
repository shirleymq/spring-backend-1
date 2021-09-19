package com.sales.market.model.purchases;


import com.sales.market.model.ModelBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class CustomerDiscountRule implements ModelBase {
    private String name;

    @Enumerated(EnumType.STRING)
    private DiscountRuleState discountRuleState;

    @ManyToOne
    private DiscountPolicy discountPolicy;

    @Temporal(TemporalType.TIMESTAMP)
    private Date activationDate = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    private Date stateDate;

    @Lob
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idusuario", referencedColumnName = "idusuario")
    private User user;

    @Column(name = "monto", precision = 13, scale = 2, nullable = false)
    private BigDecimal amount;

    @OneToMany(mappedBy = "discountRule")
    private List<CustomerDiscount> discounts = new ArrayList<CustomerDiscount>(0);
}
