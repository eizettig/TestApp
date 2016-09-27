package com.zettig.a65apps.view;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import com.zettig.a65apps.R;
import com.zettig.a65apps.model.data.Person;
import com.zettig.a65apps.model.data.Specialty;
import com.zettig.a65apps.view.fragments.PersonInfoFragment;
import com.zettig.a65apps.view.fragments.PersonListFragment;
import com.zettig.a65apps.view.fragments.SpecialtyListFragment;

import butterknife.Bind;

public class MainActivity extends AppCompatActivity implements CallBackActivity {

    private static String TAG = "TAG";
    private FragmentManager fragmentManager;
    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.toolbar_progress_bar) ProgressBar progressBar;


    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        progressBar = (ProgressBar)findViewById(R.id.toolbar_progress_bar);
        setSupportActionBar(toolbar);

        fragmentManager = getSupportFragmentManager();
        Fragment fragment = fragmentManager.findFragmentByTag(TAG);
        if (fragment == null) replaceFragment(new SpecialtyListFragment(), false);
    }

    private void replaceFragment(Fragment fragment, boolean addBackStack) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment, TAG);
        if (addBackStack) transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void showPersonList(Specialty specialty) {
        PersonListFragment personListFragment = new PersonListFragment();
        personListFragment.setFilter(specialty);
        replaceFragment(personListFragment,true);
    }

    @Override
    public void showPersonInfo(Person person) {
        PersonInfoFragment personInfoFragment = new PersonInfoFragment();
        personInfoFragment.setPerson(person);
        replaceFragment(personInfoFragment,true);
    }
}
