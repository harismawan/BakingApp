package com.harismawan.bakingapp.utils;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;

public class TestUtils {

    public static void tapRecyclerViewItem(int recyclerViewId, int position) {
        Espresso.onView(ViewMatchers.withId(recyclerViewId)).perform(RecyclerViewActions.scrollToPosition(position));
        Espresso.onView(ViewMatchers.withId(recyclerViewId)).
                perform(RecyclerViewActions.actionOnItemAtPosition(position, ViewActions.click()));
    }

    private static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static void checkTextAtRecyclerView(int recyclerViewId, int position, String text) {
        Espresso.onView(ViewMatchers.withId(recyclerViewId)).perform(RecyclerViewActions.scrollToPosition(position));
        Espresso.onView(withRecyclerView(recyclerViewId).atPosition(position)).check(
                ViewAssertions.matches(ViewMatchers.hasDescendant(ViewMatchers.withText(text))));
    }
}
