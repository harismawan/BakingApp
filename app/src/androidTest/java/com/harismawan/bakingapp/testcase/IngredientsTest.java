package com.harismawan.bakingapp.testcase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.activity.MainActivity;
import com.harismawan.bakingapp.utils.TestUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class IngredientsTest {

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
        TestUtils.checkTextAtRecyclerView(R.id.recycler_list, 0, "Ingredients");
    }

    @Test
    public void ingredientsAdapterTest() {
        TestUtils.tapRecyclerViewItem(R.id.recycler_list, 0);
    }

    @Test
    public void contentTextValueTest() {
        String[] strings = {"Graham Cracker crumbs", "unsalted butter, melted", "granulated sugar", "salt", "vanilla",
                "Nutella or other chocolate-hazelnut spread", "Mascapone Cheese(room temperature)",
                "heavy cream(cold)", "cream cheese(softened)"};
        for (int i = 0; i < strings.length; i++) {
            TestUtils.checkTextAtRecyclerView(R.id.recycler_list, i, strings[i]);
        }
    }
}
