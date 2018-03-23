package com.rigobertosl.nevergiveapp;


import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomTrainingAdapter extends ArrayAdapter<String>{

    private RecyclerView recyclerView;
    private CardExerciseAdapter adapter;
    private List<ExerciseCard> exerciseList;

    private DataBaseContract db;

    CustomTrainingAdapter(Context context, String[] titulos) {
        super(context, R.layout.layout_training, titulos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater buckysInflater = LayoutInflater.from(getContext());
        final View customView = buckysInflater.inflate(R.layout.layout_training, parent,false);

        String singleTitleItem = getItem(position);
        final TextView title = (TextView) customView.findViewById(R.id.item_title);
        final ImageButton options = (ImageButton) customView.findViewById(R.id.item_options);

        title.setText(singleTitleItem);
        options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(customView);
            }
        });

        db = new DataBaseContract(getContext());
        db.open();

        exerciseList = new ArrayList<>();
        prepareExercises(fillContentTable());

        recyclerView = (RecyclerView) customView.findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));

        adapter = new CardExerciseAdapter(this.getContext(), exerciseList);
        recyclerView.setAdapter(adapter);

        return customView;
    }

    public void showMenu(View view) {
        PopupMenu popup = new PopupMenu(view.findViewById(R.id.item_options).getContext(), view);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return true;
            }
        });// to implement on click event on items of menu
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu_training_elements, popup.getMenu());
        popup.show();
    }

    private String[] fillContentTable() {

        Cursor cursor = db.fetchAllRowsListTraining();
        ArrayList<String> aux = new ArrayList<String>();
        if(cursor.moveToFirst()){
            do {
                aux.add(cursor.getString(cursor.getColumnIndex(DataBaseContract.DataBaseEntryListTrain.COLUMN_REPETICIONES)));
            }while (cursor.moveToNext());
        }
        String[] titles = aux.toArray(new String[aux.size()]);
        return titles;
    }

    private void prepareExercises(String[]repeticiones) {
        exerciseList.add(new ExerciseCard(repeticiones[1]));
    }

    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getContext().getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}
