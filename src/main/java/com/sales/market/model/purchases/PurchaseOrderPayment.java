package com.sales.market.model.purchases;

import com.sales.market.model.Employee;
import com.sales.market.model.ModelBase;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
public class PurchaseOrderPayment extends ModelBase {

    private String bankAccountNumber;
    @Enumerated(EnumType.STRING)
    private PurchaseOrderPaymentState state;

    private String description;

    private Long purchaseOrderId;

    //MONTOPAGO
    private BigDecimal payAmount;

    //CLASEPAGO
    @Enumerated(EnumType.STRING)
    private PurchaseOrderPaymentKind purchaseOrderPaymentKind;

    @Temporal(TemporalType.DATE)
    private Date creationDate;

    @Temporal(TemporalType.DATE)
    private Date approvalDate;

    @ManyToOne
    private Employee registerEmployee;


    @ManyToOne
    private Employee approvedByEmployee;

    private String transactionNumber;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrder purchaseOrder;


}
