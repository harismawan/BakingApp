package com.harismawan.bakingapp.testcase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.utils.TestUtils;
import com.harismawan.bakingapp.activity.MainActivity;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class StepTest {

    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recipeTextValueTest() {
        String[] strings = {"Nutella Pie", "Brownies", "Yellow Cake", "Cheesecake"};
        for (int i = 0; i < strings.length; i++) {
            TestUtils.checkTextAtRecyclerView(R.id.recycler_recipe, i, strings[i]);
        }
    }

    @Test
    public void recipeAdapterTest() {
        TestUtils.tapRecyclerViewItem(R.id.recycler_recipe, 0);
    }

    @Test
    public void ingredientsTextValueTest() {
        TestUtils.checkTextAtRecyclerView(R.id.recycler_list, 1, "Introduction");
    }

    @Test
    public void ingredientsAdapterTest() {
        TestUtils.tapRecyclerViewItem(R.id.recycler_list, 1);
    }

    @Test
    public void contentTextValueTest() {
        String[] strings = {"Recipe Introduction", "Starting prep", "Prep the cookie crust.",
                "Press the crust into baking form.", "Start filling step", "Finish filling prep", "Finishing Steps"};
        for (int i = 0; i < strings.length; i++) {
            TestUtils.checkTextAtRecyclerView(R.id.recycler_list, i, strings[i]);
        }
    }
}
