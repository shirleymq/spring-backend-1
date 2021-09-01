/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market;

import com.sales.market.model.Buy;
import com.sales.market.repository.BuyRespository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DevelopmentBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private BuyRespository buyRespository;

    public DevelopmentBootstrap(BuyRespository buyRespository) {
        this.buyRespository = buyRespository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        System.out.println("evento de spring");
        Buy buy = new Buy();
        buy.setValue(BigDecimal.TEN);
        buyRespository.save(buy);
    }
}
