package com.rile.methotels.components;

import org.apache.tapestry5.annotations.Persist;
import org.apache.tapestry5.annotations.Property;

/**
 *
 * @author Stefan
 */
public class Counter {

    @Persist
    @Property
    private int count;

    void onIncrement() {
        this.count++;
    }

    void onClear() {
        this.count = 0;
    }

}
