package com.example.st31_2024_r06_sqlite;

import android.app.Activity;
import android.widget.EditText;

public class UserBeans {
    private String id;
    private String name;
    private int age;
    private String pass;

    public UserBeans(String id, String name, int age, String pass) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.pass = pass;
    }
    public void fncGetData(Activity activity) {
        EditText inId = activity.findViewById(R.id.inputId);
        id = inId.getText().toString();
    }
    public void  fncGetData(Activity activity, int resId) {
        EditText edWork = activity.findViewById(R.id.inputName);
//        name = inName.getText().toString();
    }
}
