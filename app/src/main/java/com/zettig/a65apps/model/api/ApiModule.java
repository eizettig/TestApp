package com.zettig.a65apps.model.api;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.zettig.a65apps.model.data.Person;
import com.zettig.a65apps.model.data.PersonDeserializer;
import com.zettig.a65apps.model.data.Specialty;
import com.zettig.a65apps.model.data.SpecialtyDeserializer;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiModule {
    final static String url = "http://65apps.com";

    private ApiModule() {
    }
    public static ApiInterface getApiInterface(){
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(Person.class, new PersonDeserializer())
                .registerTypeAdapter(Specialty.class, new SpecialtyDeserializer())
                .create();
        Retrofit.Builder builder = new Retrofit.Builder().
                baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

        builder.client(client);
        Log.d("retrofit","load");
        ApiInterface apiInterface = builder.build().create(ApiInterface.class);
        return apiInterface;
    }
}
