/**
 * @author: Edson A. Terceros T.
 */

package com.sales.market.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class FeatureInstance extends ModelBase {
    private String value;
    @ManyToOne
    private Feature feature;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
