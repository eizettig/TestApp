package com.zettig.a65apps.view.fragments;

import com.zettig.a65apps.model.data.Specialty;

import java.util.List;

public interface SpecialtyListView extends View {
    void showList(List<Specialty> specialties);
}

