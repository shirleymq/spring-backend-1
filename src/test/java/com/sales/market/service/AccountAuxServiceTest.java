package com.sales.market.service;

import com.sales.market.model.AccountAux;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class AccountAuxServiceTest {
    @Autowired
    private AccountAuxService accountAuxService;

    @Test
    void givenBalance100WhenDebit100TwiceShouldFail() throws ExecutionException, InterruptedException {
        assertThrows(ExecutionException.class, () -> {
            AccountAux accountAux = new AccountAux();
            accountAux.setTotalCredit(new BigDecimal("100"));
            accountAux.setTotalDebit(new BigDecimal("0"));
            accountAux.setBalance(new BigDecimal("100"));
            accountAux = accountAuxService.save(accountAux);

            AccountAux finalAccountAux = accountAux;
            CompletableFuture<AccountAux> completableFutureFirstOperation =
                    CompletableFuture.supplyAsync(() -> accountAuxService.debit(finalAccountAux.getId(), new BigDecimal(
                            "100"))).orTimeout(60, TimeUnit.SECONDS);
            CompletableFuture<AccountAux> completableFutureSecondOperation =
                    CompletableFuture.supplyAsync(() -> accountAuxService.debit(finalAccountAux.getId(), new BigDecimal(
                            "100"))).orTimeout(60, TimeUnit.SECONDS);

            completableFutureFirstOperation.get();
            completableFutureSecondOperation.get();
            AccountAux accountAuxAfterOperations = accountAuxService.getById(accountAux.getId());

            //felicidades lograste sacar 200 de una cuenta que solo tiene balance 100 de forma satisfactoria
            System.out.println("debit" + accountAuxAfterOperations.getTotalDebit());
            System.out.println("credit" + accountAuxAfterOperations.getTotalCredit());
            System.out.println("balance" + accountAuxAfterOperations.getBalance());
            assertEquals(0, accountAuxAfterOperations.getBalance().compareTo(new BigDecimal("0")));
        });
    }
}
