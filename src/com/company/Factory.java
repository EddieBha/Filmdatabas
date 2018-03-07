package com.company;

public class Factory {

   public static IMyListModel createListModel() {

        return new FilteredListModel();


    }

    public static Movie createMovie(String title, String director, String year) throws IllegalArgumentException, NumberFormatException{

       if(title.isEmpty())
           throw new IllegalArgumentException("Titel är tomt! ");
       if(director.isEmpty())
           throw new IllegalArgumentException("Regissör är tomt! ");
       if(year.isEmpty())
           throw new IllegalArgumentException("År är tomt! ");

       return new Movie(title,director,Integer.parseInt(year));

    }
}