/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;

import com.sales.market.model.Buy;

import java.math.BigDecimal;

public interface BuyRespository extends GenericRepository<Buy> {
    public Buy findAllByValue(BigDecimal value);
}
