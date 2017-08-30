package com.harismawan.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.espresso.action.ViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.harismawan.bakingapp.activity.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recipeAdapterTest() {
        tapRecyclerViewItem(R.id.recycler_recipe, 1);
    }

    public static void tapRecyclerViewItem(int recyclerViewId, int position) {
        Espresso.onView(ViewMatchers.withId(recyclerViewId)).perform(RecyclerViewActions.scrollToPosition(position));
        Espresso.onView(ViewMatchers.withId(R.id.recycler_recipe)).
                perform(RecyclerViewActions.actionOnItemAtPosition(position, ViewActions.click()));
    }
}
