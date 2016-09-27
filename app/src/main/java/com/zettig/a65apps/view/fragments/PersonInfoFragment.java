package com.zettig.a65apps.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.*;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import com.zettig.a65apps.R;
import com.zettig.a65apps.model.data.Person;
import java.text.SimpleDateFormat;
import butterknife.Bind;
import butterknife.ButterKnife;


public class PersonInfoFragment extends Fragment {

    private final String BUNDLE_KEY= "BUNDLE_PERSON_INFO_KEY";

    @Bind(R.id.person_info_photo) ImageView photo;
    @Bind(R.id.person_info_fname) TextView fname;
    @Bind(R.id.person_info_lname) TextView lname;
    @Bind(R.id.person_info_birthday) TextView birthday;
    @Bind(R.id.person_info_age) TextView age;
    @Bind(R.id.person_info_specialty) TextView specialty;
    Person person;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.person_info,container,false);
        ButterKnife.bind(this,view);
        if (savedInstanceState!=null) {
            person = (Person) savedInstanceState.getSerializable(BUNDLE_KEY);
        }
        showPersonInfo(person);
        return view;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    private void showPersonInfo(Person person){
        Picasso.with(getContext())
                .load(person.getAvatrUrl())
                .placeholder(R.drawable.avatar_error)
                .error(R.drawable.avatar_error)
                .into(photo);
        fname.setText(person.getFName());
        lname.setText(person.getLName());
        if (person.getBirthday()!=null){
            age.setText(String.valueOf(person.getAge()));
            SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            birthday.setText(" " + df.format(person.getBirthday()));
        } else {
            age.setText("-");
            birthday.setText("-");
        }
        for (int i=0;i<=person.getSpecialty().size()-1;i++){
            specialty.append(person.getSpecialty().get(i).getName());
            if (i<person.getSpecialty().size()-1){
                specialty.append(", ");
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (person!=null){
            outState.putSerializable(BUNDLE_KEY,person);
        }
    }
}
