/**
 * @author: Edson A. Terceros T.
 * 17
 */

package com.sales.market.dto;

import com.sales.market.model.ModelBase;

import java.math.BigDecimal;

public class BuyDto extends ModelBase {

    private BigDecimal value;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }
}
