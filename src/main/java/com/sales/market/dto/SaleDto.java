/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.dto;

import com.sales.market.model.Sale;

import java.util.Date;

public class SaleDto extends DtoBase<Sale> {
    private Long employeeId;
    private Date date;
    private String description;

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
