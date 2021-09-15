/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.ItemInstance;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.ItemInstanceRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemInstanceServiceImpl extends GenericServiceImpl<ItemInstance> implements ItemInstanceService {
    private final ItemInstanceRepository repository;

    public ItemInstanceServiceImpl(ItemInstanceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<ItemInstance> getRepository() {
        return repository;
    }
}
