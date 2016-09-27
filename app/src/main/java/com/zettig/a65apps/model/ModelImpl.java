package com.zettig.a65apps.model;

import com.zettig.a65apps.model.api.ApiInterface;
import com.zettig.a65apps.model.api.ApiModule;
import com.zettig.a65apps.model.data.Person;
import com.zettig.a65apps.model.data.PersonList;
import com.zettig.a65apps.model.data.Specialty;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

public class ModelImpl implements Model{

    private static ModelImpl ourInstance = new ModelImpl();

    public static ModelImpl getInstance() {
        return ourInstance;
    }

    private ModelImpl() {
    }

    protected ApiInterface apiInterface = ApiModule.getApiInterface();

    Observable<List<Person>> list;

    @Override
    public Observable<List<Person>> getPersonList() {
        return getList()
                .cache();
    }

    @Override
    public Observable<List<Specialty>> getSpecialtyList() {
        return getList()
                .flatMap(new Func1<List<Person>, Observable<Person>>() {
                    @Override
                    public Observable<Person> call(List<Person> persons) {
                        return Observable.from(persons);
                    }
                })
                .flatMap(new Func1<Person, Observable<Specialty>>() {
                    @Override
                    public Observable<Specialty> call(Person person) {
                        return Observable.from(person.getSpecialty());
                    }
                }).distinct().toList()
                .cache();
    }
    private Observable<List<Person>> getList(){
        if (list==null) {
            list = apiInterface.getPersonList()
                    .flatMap(new Func1<PersonList, Observable<List<Person>>>() {
                        @Override
                        public Observable<List<Person>> call(PersonList personList) {
                            return Observable.from(personList.getList()).toList();
                        }
                    });
        }
        return list;
    }
}
