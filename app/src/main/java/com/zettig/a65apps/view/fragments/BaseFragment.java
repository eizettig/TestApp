package com.zettig.a65apps.view.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.zettig.a65apps.presenter.Presenter;
import com.zettig.a65apps.view.CallBackActivity;


public abstract class BaseFragment extends Fragment implements View{
    CallBackActivity callBackActivity;
    Presenter presenter;

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBackActivity = (CallBackActivity)getActivity();
    }

    @Override
    public void showError(String error) {
    }

    @Override
    public void showLoading() {
        callBackActivity.showProgressBar();
    }

    @Override
    public void hideLoading() {
        callBackActivity.hideProgressBar();
    }
}
