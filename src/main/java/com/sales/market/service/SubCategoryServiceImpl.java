/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.SubCategory;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SubCategoryServiceImpl extends GenericServiceImpl<SubCategory> implements SubCategoryService {
    private final SubCategoryRepository repository;

    public SubCategoryServiceImpl(SubCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected GenericRepository<SubCategory> getRepository() {
        return repository;
    }

    @Override
    public Set<SubCategory> findAllByCategoryId(Long id) {
        return new HashSet<>(repository.findAllByCategoryId(id));
    }
}
