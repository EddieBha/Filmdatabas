package com.company;


public class Movie {

    private String title;
    private String director;
    private int year;

    public Movie(String title, String director, int year) {
        this.setTitle(title);
        this.setDirector(director);
        this.setYear(year);
    }

    public String getTitle() {
        return title;
    }

    public String getDirector() {
        return director;
    }

    public int getYear() {
        return year;
    }



    public String toString(){
        return "Title: " + title + ", Director: " + director + ", Year: " + year;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
