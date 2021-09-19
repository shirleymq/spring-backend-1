package com.sales.market.model.purchases;

import com.sales.market.model.Item;
import com.sales.market.model.ModelBase;

import javax.persistence.*;
import java.math.BigDecimal;

@NamedQueries({
        @NamedQuery(name = "PurchaseOrderDetail.maxByPurchaseOrder",
                query = "select max(p.detailNumber) from PurchaseOrderDetail p where p.purchaseOrder=:purchaseOrder"),
        @NamedQuery(name = "PurchaseOrderDetail.findByPurchaseOrder", query = "select p from PurchaseOrderDetail p" +
                " where p.purchaseOrder=:purchaseOrder"),
        @NamedQuery(name = "PurchaseOrderDetail.countByPurchaseOrder", query = "select count(p) from " +
                "PurchaseOrderDetail p" +
                " where p.purchaseOrder=:purchaseOrder"),
        @NamedQuery(name = "PurchaseOrderDetail.sumTotalAmounts",
                query = "select sum(detail.totalAmount) from PurchaseOrderDetail detail where detail.purchaseOrder " +
                        "=:purchaseOrder"),
        @NamedQuery(name = "PurchaseOrderDetail.countByProductItemAndPurchaseOrder",
                query = "select sum(detail.id) from PurchaseOrderDetail detail" +
                        " where detail.purchaseOrder =:purchaseOrder and detail.productItemCode=:productItemCode"),
        @NamedQuery(name = "PurchaseOrderDetail.countByProductItemAndPurchaseOrderDetail",
                query = "select sum(detail.id) from PurchaseOrderDetail detail" +
                        " where detail<>:detail and detail.purchaseOrder =:purchaseOrder and detail" +
                        ".productItemCode=:productItemCode")
})
@Entity
public class PurchaseOrderDetail extends ModelBase {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private PurchaseOrder purchaseOrder;

    @Column(nullable = true, length = 6)
    private String purchaseMeasureCode;

    @Column(nullable = false)
    private String orderNumber;

    @Column(nullable = false, updatable = false)
    private Long detailNumber;

    @Column(precision = 16, scale = 2, nullable = false)
    private BigDecimal requestedQuantity;

    @Column(precision = 16, scale = 6)
    private BigDecimal unitCost;

    @Column(precision = 16, scale = 6)
    private BigDecimal totalAmount;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Item item;

    @Column(nullable = false)
    private String itemCode;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "NO_CIA", nullable = false, updatable = false, insertable = false),
            @JoinColumn(name = "COD_MED", nullable = false, updatable = false, insertable = false)
    })
    private MeasureUnit purchaseMeasureUnit;
}
