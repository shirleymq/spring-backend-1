/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market;

import com.sales.market.dto.BuyDto;
import com.sales.market.model.Buy;
import com.sales.market.service.BuyService;
import com.sales.market.service.GenericService;
import controller.GenericController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/buys")
public class BuyController extends GenericController<Buy, BuyDto> {
    private BuyService service;

    public BuyController(BuyService service) {
        this.service = service;
    }

    /*
    Especificar en el hijo cuando el generico no tiene especificado los tipos de MApping
    @Override
    @DeleteMapping(value = "/{id}")
    protected void deleteElement(Long id) {
        super.deleteElement(id);
    }*/

    @Override
    protected GenericService getService() {
        return service;
    }
}
