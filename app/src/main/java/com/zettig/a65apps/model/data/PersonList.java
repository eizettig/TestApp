package com.zettig.a65apps.model.data;

import java.util.ArrayList;
import java.util.List;

public class PersonList {

    private List<Person> response = new ArrayList<Person>();

    public List<Person> getList() {
        return response;
    }

    public void setList(List<Person> response) {
        this.response = response;
    }

}