package com.company;

import javax.swing.*;
import com.company.FilterStrategy.*;

public interface IMyListModel extends ListModel<Movie>{

    void update(int index, Movie text);

    void add(Movie text);

    void remove(int index);

    void clear();

    void filter(FilterStrategy strategy);


}
