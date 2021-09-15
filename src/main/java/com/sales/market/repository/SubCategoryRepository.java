/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.repository;


import com.sales.market.model.SubCategory;

import java.util.List;

public interface SubCategoryRepository extends GenericRepository<SubCategory> {
    List<SubCategory> findAllByCategoryId(Long categoryId);
}
