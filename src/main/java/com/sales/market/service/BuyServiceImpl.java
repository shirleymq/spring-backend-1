/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Buy;
import com.sales.market.repository.BuyRepository;
import com.sales.market.repository.GenericRepository;
import org.springframework.stereotype.Service;

@Service
public class BuyServiceImpl extends GenericServiceImpl<Buy> implements BuyService {
    private final BuyRepository repository;

    public BuyServiceImpl(BuyRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<Buy> getRepository() {
        return repository;
    }
}
