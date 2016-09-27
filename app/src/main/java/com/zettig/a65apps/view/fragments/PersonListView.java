package com.zettig.a65apps.view.fragments;

import com.zettig.a65apps.model.data.Person;
import java.util.List;

public interface PersonListView extends View{
    void showList (List<Person> persons);
}
