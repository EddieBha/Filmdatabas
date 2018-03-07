package com.company;

import java.util.ArrayList;

public class FilteredListModel extends MyListModel {

    ArrayList<Movie> model = new ArrayList<>();
    FilterStrategy filter = new ShowAll();

    public void filter(FilterStrategy filterStrategy) {
        filter = filterStrategy;
        super.clear();

        for (Movie o : model) {
            if (filterStrategy.filter(o)) {
                super.add(o);
            }
        }
    }

        @Override
        public void add(Movie text) {
            model.add(text);
            filter(filter);
        }

        @Override
        public void remove(int selectedIndex) {
            model.remove(super.getElementAt(selectedIndex));
            filter(filter);
        }

        @Override
        public void clear() {
            model.clear();
            filter(filter);
        }

    @Override
    public void update(int index, Movie text) {
//        super.update(index, text);
        Movie s = filmer.get(index); // hämta elementet på index
//        model.remove(s);
        model.set(model.indexOf(s),text); // sätta nya elementet på index
        filter(filter);


    }
}



