package com.zettig.a65apps.model;

import com.zettig.a65apps.model.data.Person;
import com.zettig.a65apps.model.data.Specialty;
import java.util.List;
import rx.Observable;

public interface Model {
    Observable<List<Person>> getPersonList();
    Observable<List<Specialty>> getSpecialtyList();
}
