/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.Category;
import com.sales.market.model.SubCategory;
import com.sales.market.repository.GenericRepository;
import com.sales.market.repository.SubCategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
public class SubCategoryServiceImpl extends GenericServiceImpl<SubCategory> implements SubCategoryService {
    private final SubCategoryRepository repository;
    private final CategoryService categoryService;

    public SubCategoryServiceImpl(SubCategoryRepository repository, CategoryService categoryService) {
        this.repository = repository;
        this.categoryService = categoryService;
    }

    @Override
    public SubCategory save(SubCategory model) {
        SubCategory subCategory = super.save(model);
        Category category = subCategory.getCategory();
        if (category != null && category.getId() != null) {
            subCategory.setCategory(categoryService.findById(category.getId()));
        }
        return subCategory;
    }

    @Override
    protected GenericRepository<SubCategory> getRepository() {
        return repository;
    }

    @Override
    public Set<SubCategory> findAllById(Long id) {
        return new HashSet<>(repository.findAllById(Collections.singleton(id)));
    }
}
