/**
 * @author: Edson A. Terceros T.
 * 17
 */

package com.sales.market.model;

import com.sales.market.dto.BuyDto;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class Buy extends ModelBase<BuyDto> {

    @Column(precision = 10, scale = 5)
    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
