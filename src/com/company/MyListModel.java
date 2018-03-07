package com.company;


import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;

public class MyListModel implements IMyListModel { // tar efter egenskaper från interfacet IMyListModel.


    ArrayList<Movie> filmer = new ArrayList<>();

    ArrayList<ListDataListener> listener = new ArrayList<>();

    @Override
    public int getSize() {
        return filmer.size();
    } // returnerar antalet element på filmer

    @Override
    public Movie getElementAt(int index) { // hämta elemement på specifikt index(plats i listan)
        return filmer.get(index);

    }


    public void addListDataListener(ListDataListener l) {

        listener.add(l);

    }

    public void removeListDataListener(ListDataListener l) {
        listener.remove(l);

    }

    public void update(int index, Movie text) { // update metod

        filmer.set(index, text);
        for (ListDataListener l : listener) {
            l.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, index, index));
        }
    }

    public void add(Movie text) { // lägg till metod
        if (!filmer.contains(text))
            filmer.add(text);
        for (ListDataListener l : listener) {
            l.intervalAdded(new ListDataEvent(this, ListDataEvent.INTERVAL_ADDED, filmer.size() - 1, filmer.size() - 1));
        }
    }


    public void remove(int selectedIndex) { // ta bort metod.
        filmer.remove(selectedIndex);
        for (ListDataListener l : listener) {
            l.intervalRemoved(new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, selectedIndex, selectedIndex));
        }
    }



    public void clear() { // rensnings metod
        for (ListDataListener l : listener) {
            l.intervalRemoved(new ListDataEvent(this, ListDataEvent.INTERVAL_REMOVED, 0, filmer.size()));
        }
        filmer.clear();
    }

    @Override
    public void filter(FilterStrategy strategy) {
    }
}




