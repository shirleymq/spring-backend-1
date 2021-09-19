package com.sales.market.model.purchases;

import com.sales.market.model.ModelBase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class PurchaseOrderPayment extends ModelBase {

    private String description;

    //MONTOPAGO
    private BigDecimal payAmount;

    //CLASEPAGO
    @Enumerated(EnumType.STRING)
    private PurchaseOrderPaymentKind purchaseOrderPaymentKind;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrder purchaseOrder;
}
