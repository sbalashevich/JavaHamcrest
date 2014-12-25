/*  Copyright (c) 2000-2006 hamcrest.org
 */
package org.hamcrest.number;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

import static java.lang.Math.abs;


/**
 * Is the value a number equal to a value within some range of
 * acceptable error?
 */
public class IsCloseTo extends TypeSafeMatcher<Double> {
    private final double delta;
    private final double value;

    public IsCloseTo(double value, double error) {
        this.delta = error;
        this.value = value;
    }

    @Override
    public boolean matchesSafely(Double item) {
        return actualDelta(item) <= 0.0;
    }

    @Override
    public void describeMismatchSafely(Double item, Description mismatchDescription) {
      mismatchDescription.appendValue(item)
                         .appendText(" differed by ")
                         .appendValue(actualDelta(item))
                         .appendText(" more than delta ")
                         .appendValue(delta);
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("a numeric value within ")
                .appendValue(delta)
                .appendText(" of ")
                .appendValue(value);
    }

    private double actualDelta(Double item) {
      return abs(item - value) - delta;
    }
}
