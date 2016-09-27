package com.zettig.a65apps.view;

import com.zettig.a65apps.model.data.Person;
import com.zettig.a65apps.model.data.Specialty;

public interface CallBackActivity {
    void showPersonList(Specialty specialty);

    void showPersonInfo(Person person);

    void showProgressBar();

    void hideProgressBar();
}
