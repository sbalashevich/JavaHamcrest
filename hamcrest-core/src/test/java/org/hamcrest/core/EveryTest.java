package org.hamcrest.core;

import org.hamcrest.Matcher;
import org.hamcrest.collection.MatchingIterables;
import org.junit.Test;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.hamcrest.AbstractMatcherTest.*;
import static org.hamcrest.core.StringContains.containsString;

public final class EveryTest {

    private final Matcher<Iterable<? extends String>> matcher = MatchingIterables.everyItem(containsString("a"));

    @Test public void
    copesWithNullsAndUnknownTypes() {
        assertNullSafe(matcher);
        assertUnknownTypeSafe(matcher);
    }

    @Test public void
    matchesOnlyWhenEveryItemMatches() {
        assertMatches(matcher, asList("AaA", "BaB", "CaC"));
        assertDoesNotMatch(matcher, asList("AaA", "BXB", "CaC"));
    }

    @Test public void
    matchesEmptyLists() {
        assertMatches("didn't match empty list", matcher, new ArrayList<String>());
    }

    @Test public void
    describesItself() {
        assertDescription("every item is a string containing \"a\"", matcher);
    }

    @Test public void
    describesAMismatch() {
        assertMismatchDescription("an item was \"BXB\"", matcher, asList("BXB"));
    }
}

