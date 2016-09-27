package com.zettig.a65apps.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.design.widget.Snackbar;
import com.zettig.a65apps.R;
import com.zettig.a65apps.model.data.Specialty;
import com.zettig.a65apps.presenter.SpecialtyListPresenter;
import com.zettig.a65apps.view.CallBackActivity;
import com.zettig.a65apps.view.adapters.SpecialtyRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SpecialtyListFragment extends BaseFragment implements SpecialtyListView {

    private final String BUNDLE_KEY = "BUNDLE_SPECIALTY_LIST_KEY";
    private SpecialtyRecyclerViewAdapter adapter = new SpecialtyRecyclerViewAdapter();
    private List<Specialty> list;

    @Bind(R.id.recycler_view) RecyclerView recyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        presenter = new SpecialtyListPresenter(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.recycler_view,container,false);
        ButterKnife.bind(this,view);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(getContext(),recyclerView, new RecyclerItemClickListener.OnItemClickListener(){
            @Override
            public void onItemClick(View view, int position) {
                CallBackActivity callBackActivity = (CallBackActivity) getActivity();
                callBackActivity.showPersonList(list.get(position));
            }
        }));
        if (savedInstanceState!=null){
            list = (List<Specialty>)savedInstanceState.getSerializable(BUNDLE_KEY);
        }else {
            presenter.loadData();
        }
        return view;
    }

    @Override
    public void showList(List<Specialty> specialties) {
        this.list = specialties;
        adapter.showList(specialties);
    }

    private void makeToast(String text) {
        Snackbar.make(recyclerView,text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(BUNDLE_KEY,new ArrayList<>(list));
    }



}
