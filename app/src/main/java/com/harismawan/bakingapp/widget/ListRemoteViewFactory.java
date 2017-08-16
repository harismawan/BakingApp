package com.harismawan.bakingapp.widget;

import android.content.Context;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;
import com.harismawan.bakingapp.R;
import com.harismawan.bakingapp.database.DatabaseHelper;
import com.harismawan.bakingapp.database.Query;
import com.harismawan.bakingapp.model.Ingredient;

import java.util.ArrayList;

public class ListRemoteViewFactory implements RemoteViewsService.RemoteViewsFactory {

    private Context context;
    private Query query;
    private ArrayList<Ingredient> ingredients;

    public ListRemoteViewFactory(Context context) {
        this.context = context;

        DatabaseHelper db = new DatabaseHelper(context);
        query = new Query(db);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDataSetChanged() {
        ingredients = query.getWidgetData();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return ingredients.size();
    }

    @Override
    public RemoteViews getViewAt(int i) {
        Ingredient ingredient = ingredients.get(i);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.item_widget);
        views.setTextViewText(R.id.ingredient_name, ingredient.ingredient);
        views.setTextViewText(R.id.ingredient_quantity, ingredient.quantity + " " + ingredient.measure);

        return views;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}
