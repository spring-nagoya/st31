package com.example.a2025_st42_r01_fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    FragmentManager fragMana;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        fragMana = getSupportFragmentManager();

        Button btnMain = findViewById(R.id.btnMain);
        Button btnSecond = findViewById(R.id.btnSecond);
        btnMain.setOnClickListener(this);
        btnSecond.setOnClickListener(this);

        btnMain.isEnabled();



//
//        Button button = findViewById(R.id.btnMain);
//        Button button2 = findViewById(R.id.btnSecond);
//        button.setOnClickListener(v -> {
//            FragmentTransaction trans;
//            trans = fragMana.beginTransaction();
//
//            trans.replace(R.id.fragmentContainerView, new SecondFragment());
//
//            trans.commit();
//
////            btnMainの無効化
//            button.setEnabled(false);
//            button.setAlpha(0.5f);
//
//            button2.setEnabled(true);
//            button2.setAlpha(1.0f);
//        });
//
//
//        button2.setOnClickListener(v -> {
//            FragmentTransaction trans;
//            trans = fragMana.beginTransaction();
//
//            trans.replace(R.id.fragmentContainerView, new BlankFragment());
//
//            trans.commit();
//
////            btnSecondの無効化
//            button2.setEnabled(false);
//            button2.setAlpha(0.5f);
//
//            button.setEnabled(true);
//            button.setAlpha(1.0f);
//        });
    }
    @Override
    public  void onClick(View v) {
        FragmentTransaction trans;
        trans = fragMana.beginTransaction();

        Fragment fragment = null;
        if (v.getId() == R.id.btnMain) {
            fragment = new SecondFragment();
        } else if (v.getId() == R.id.btnSecond) {
            fragment = new BlankFragment();
        }
        if (fragment != null) {
            trans.replace(R.id.fragmentContainerView, fragment);
        }

//        boolean enabledMain = true, enabledSecond = true;
//
//        if (v.getId() == R.id.btnMain) {
//            new  SecondFragment();
//            trans.replace(R.id.fragmentContainerView, new SecondFragment());
////            enabledMain = false;
//        }
//        else if (v.getId() == R.id.btnSecond) {
//            new BlankFragment();
//            trans.replace(R.id.fragmentContainerView, new BlankFragment());
////            enabledSecond = false;
//        }
        trans.commit();
//        findViewById(R.id.btnMain).setEnabled(enabledMain);
        findViewById(R.id.btnMain).setEnabled(!findViewById(R.id.btnMain).isEnabled());
        findViewById(R.id.btnMain).setAlpha(!findViewById(R.id.btnMain).isEnabled() ? 1.0f : 0.5f);
        findViewById(R.id.btnSecond).setEnabled(!findViewById(R.id.btnSecond).isEnabled());
        findViewById(R.id.btnSecond).setAlpha(!findViewById(R.id.btnSecond).isEnabled() ? 1.0f : 0.5f);
    }
}