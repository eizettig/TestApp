package com.zettig.a65apps.presenter;

import com.zettig.a65apps.model.Model;
import com.zettig.a65apps.model.ModelImpl;
import com.zettig.a65apps.model.data.Person;
import com.zettig.a65apps.model.data.Specialty;
import com.zettig.a65apps.view.fragments.PersonListView;
import java.io.IOException;
import java.util.List;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;


public class PersonListPresenter implements Presenter {

    Subscription subscription = Subscriptions.empty();
    Model model = ModelImpl.getInstance();
    PersonListView view;
    Specialty specialty;

    public PersonListPresenter(PersonListView view,Specialty specialty) {
        this.view = view;
        this.specialty = specialty;
    }

    @Override
    public void onStop() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void loadData() {
        if (!subscription.isUnsubscribed()) {
            subscription.unsubscribe();
        }
        view.showLoading();
        subscription = model.getPersonList()
                .flatMap(new Func1<List<Person>, Observable<Person>>() {
                    @Override
                    public Observable<Person> call(List<Person> persons) {
                        return Observable.from(persons);
                    }
                })
                .filter(new Func1<Person, Boolean>() {
                    @Override
                    public Boolean call(Person person) {
                        return person.getSpecialty().contains(specialty);
                    }
                })
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Person>>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof IOException){
                            view.showError("Check the Internet connection");
                        } else
                            view.showError("Something wrong :/");
                        view.hideLoading();
                    }
                    @Override
                    public void onNext(List<Person> persons) {
                        view.showList(persons);
                    }
                });
    }

}
