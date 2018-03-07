package com.company;

public class Contains implements FilterStrategy{
    private String filtertext;

   

    public Contains(String filtertext){
        this.filtertext = filtertext.toLowerCase();


    }

    @Override
    public boolean filter(Movie s) {
        return s.getTitle().toLowerCase().contains(filtertext);


    }
}

