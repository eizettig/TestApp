package com.zettig.a65apps.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.zettig.a65apps.R;
import com.zettig.a65apps.model.data.Person;
import com.zettig.a65apps.model.data.Specialty;
import com.zettig.a65apps.presenter.PersonListPresenter;
import com.zettig.a65apps.view.CallBackActivity;
import com.zettig.a65apps.view.adapters.PersonRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PersonListFragment extends BaseFragment implements PersonListView{
    private final String BUNDLE_KEY = "BUNDLE_PERSONLIST_INFO_KEY";

    List<Person> persons;
    Specialty specialty = new Specialty();
    PersonRecyclerViewAdapter adapter = new PersonRecyclerViewAdapter();

    @Bind(R.id.recycler_view2) RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new PersonListPresenter(this, specialty);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view2,container,false);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),recyclerView, new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                CallBackActivity callBackActivity = (CallBackActivity) getActivity();
                callBackActivity.showPersonInfo(persons.get(position));
            }
        }));
        if (savedInstanceState!=null){
            persons = (List<Person>)savedInstanceState.getSerializable(BUNDLE_KEY);
        } else {
            presenter.loadData();
        }
        return view;
    }

    @Override
    public void showList(List<Person> persons) {
        this.persons = persons;
        adapter.showList(persons);
    }

    @Override
    public void showError(String error) {
        makeToast("Something wrong :/");
    }

    private void makeToast(String error) {
        Toast.makeText(null,error,Toast.LENGTH_SHORT).show();
    }


    public void setFilter(Specialty specialty){
        this.specialty = specialty;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_KEY,new ArrayList<>(persons));
    }
}
