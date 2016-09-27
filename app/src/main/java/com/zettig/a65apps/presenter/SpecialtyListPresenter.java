package com.zettig.a65apps.presenter;

import com.zettig.a65apps.model.Model;
import com.zettig.a65apps.model.ModelImpl;
import com.zettig.a65apps.model.data.Specialty;
import com.zettig.a65apps.view.fragments.SpecialtyListView;
import java.io.IOException;
import java.util.List;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

public class SpecialtyListPresenter implements Presenter{
    SpecialtyListView view;
    Subscription subscription = Subscriptions.empty();
    Model model = ModelImpl.getInstance();

    public SpecialtyListPresenter(SpecialtyListView view) {
        this.view = view;
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
        subscription = model.getSpecialtyList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new Observer<List<Specialty>>() {
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
                public void onNext(List<Specialty> specialties) {
                    view.showList(specialties);
                }
            });
    }

}
