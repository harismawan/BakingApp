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
public class InstrumentTest {

    @Rule public ActivityTestRule<MainActivity> mainActivityActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void recipeTextValueTest() {
        String[] strings = {"Nutella Pie", "Brownies", "Yellow Cake", "Cheesecake"};
        for (int i = 0; i < strings.length; i++) {
            TestUtils.checkTextAtRecyclerView(R.id.recycler_recipe, i, strings[i]);
        }
    }

    private void ingredientTest(int position, String[] strings) {
        TestUtils.tapRecyclerViewItem(R.id.recycler_recipe, position);

        TestUtils.checkTextAtRecyclerView(R.id.recycler_list, 0, "Ingredients");

        TestUtils.tapRecyclerViewItem(R.id.recycler_list, 0);

        for (int i = 0; i < strings.length; i++) {
            TestUtils.checkTextAtRecyclerView(R.id.recycler_list, i, strings[i]);
        }
    }

    private void stepTest(int position, String[] strings) {
        TestUtils.tapRecyclerViewItem(R.id.recycler_recipe, position);

        TestUtils.checkTextAtRecyclerView(R.id.recycler_list, 1, "Introduction");

        TestUtils.tapRecyclerViewItem(R.id.recycler_list, 1);

        for (int i = 0; i < strings.length; i++) {
            TestUtils.checkTextAtRecyclerView(R.id.recycler_list, i, strings[i]);
        }
    }

    @Test
    public void ingredientTest1() {
        ingredientTest(0, new String[] {"Graham Cracker crumbs", "unsalted butter, melted", "granulated sugar",
                "salt", "vanilla", "Nutella or other chocolate-hazelnut spread", "Mascapone Cheese(room temperature)",
                "heavy cream(cold)", "cream cheese(softened)"});
    }

    @Test
    public void stepTest1() {
        stepTest(0, new String[] {"Recipe Introduction", "Starting prep", "Prep the cookie crust.",
                "Press the crust into baking form.", "Start filling prep", "Finish filling prep", "Finishing Steps"});
    }

    @Test
    public void ingredientTest2() {
        ingredientTest(1, new String[] {"Bittersweet chocolate (60-70% cacao)", "unsalted butter", "granulated sugar",
                "light brown sugar", "large eggs", "vanilla extract", "all purpose flour", "cocoa powder", "salt", "semisweet chocolate chips"});
    }

    @Test
    public void stepTest2() {
        stepTest(1, new String[] {"Recipe Introduction", "Starting prep", "Melt butter and bittersweet chocolate.",
                "Add sugars to wet mixture.", "Mix together dry ingredients.", "Add eggs.", "Add dry mixture to wet mixture.",
                "Add batter to pan.", "Remove pan from oven.", "Cut and serve."});
    }

    @Test
    public void ingredientTest3() {
        ingredientTest(2, new String[] {"sifted cake flour", "granulated sugar", "baking powder",
                "salt", "vanilla extract, divided", "egg yolks", "whole milk", "unsalted butter, softened and cut into 1 in. cubes",
                "egg whites", "melted and cooled bittersweet or semisweet chocolate"});
    }

    @Test
    public void stepTest3() {
        stepTest(2, new String[]{"Recipe Introduction", "Starting prep", "Combine dry ingredients.",
                "Prepare wet ingredients.", "Add butter and milk to dry ingredients.", "Add egg mixture to batter.",
                "Pour batter into pans.", "Begin making buttercream.", "Prepare egg whites.", "Beat egg whites to stiff peaks.",
                "Add butter to egg white mixture.", "Finish buttercream icing.", "Frost cakes."});
    }

    @Test
    public void ingredientTest4() {
        ingredientTest(3, new String[] {"Graham Cracker crumbs", "unsalted butter, melted", "granulated sugar",
                "salt", "vanilla,divided", "cream cheese, softened", "large whole eggs", "large egg yolks",
                "heavy cream"});
    }

    @Test
    public void stepTest4() {
        stepTest(3, new String[] {"Recipe Introduction", "Starting prep.", "Prep the cookie crust.",
                "Start water bath.", "Prebake cookie crust. ", "Mix cream cheese and dry ingredients.", "Add eggs.",
                "Add heavy cream and vanilla.", "Pour batter in pan.", "Bake the cheesecake.", "Turn off oven and leave cake in.",
                "Remove from oven and cool at room temperature.", "Final cooling and set."});
    }
}
