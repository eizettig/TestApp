package com.zettig.a65apps.model.api;

import com.zettig.a65apps.model.data.PersonList;
import retrofit2.http.GET;
import rx.Observable;

public interface ApiInterface {
    @GET("images/testTask.json")
    Observable<PersonList> getPersonList();
}
