/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.service;

import com.sales.market.model.SubCategory;

import java.util.Set;

public interface SubCategoryService extends GenericService<SubCategory> {

    Set<SubCategory> findAllByCategoryId(Long id);
}
