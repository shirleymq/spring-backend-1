/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;


import javax.persistence.Entity;
import java.math.BigDecimal;

@Entity
public class AccountAux extends ModelBase {

    private BigDecimal totalDebit;
    private BigDecimal totalCredit;
    private BigDecimal balance;
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }*/

    public BigDecimal getTotalDebit() {
        return totalDebit;
    }

    public void setTotalDebit(BigDecimal totalDebit) {
        this.totalDebit = totalDebit;
    }

    public BigDecimal getTotalCredit() {
        return totalCredit;
    }

    public void setTotalCredit(BigDecimal totalCredit) {
        this.totalCredit = totalCredit;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
