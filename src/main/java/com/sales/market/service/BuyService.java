/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Buy;
import com.sales.market.repository.BuyRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyService {
    private final BuyRepository repository;

    public BuyService(BuyRepository repository) {
        this.repository = repository;
    }

    public Buy getById(Long id) {
        return repository.getById(id);
    }
}
