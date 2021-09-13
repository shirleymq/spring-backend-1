/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.controller;

import com.sales.market.dto.SubCategoryDto;
import com.sales.market.model.SubCategory;
import com.sales.market.service.GenericService;
import com.sales.market.service.SubCategoryService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/subcategories")
public class SubCategoryController extends GenericController<SubCategory, SubCategoryDto> {
    private SubCategoryService service;

    public SubCategoryController(SubCategoryService service) {
        this.service = service;
    }

    @Override
    protected GenericService getService() {
        return service;
    }
}
