package com.company;

public class ShowAll implements FilterStrategy {

    @Override
    public boolean filter(Movie s) {
        return true;
    }
}
