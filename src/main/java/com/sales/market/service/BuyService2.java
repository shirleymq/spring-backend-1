/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Buy;
import com.sales.market.repository.BuyRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyService2 {
    private final BuyRepository repository;

    public BuyService2(BuyRepository repository) {
        this.repository = repository;
    }

    public Buy getById(Long id) {
        return repository.getById(id);
    }
}
