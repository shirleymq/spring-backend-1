/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import javax.persistence.Entity;
import javax.persistence.Id;

/*Cajero, Acomodador, Almacenero*/
@Entity
public class Position {

    /*este es un pk en la bd*/

    @Id
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
