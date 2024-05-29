package com.example.st31_2024_project;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        TextView strView = findViewById(R.id.textV);
        strView.setText("はろー");
    }

    @Override
    public void onClick(View v) {
        EditText edt = findViewById(R.id.edText);
        Editable ed = edt.getText();
        String strName = ed.toString();

        TextView strView = findViewById(R.id.textV);
        strView.setText(strName);
    }

//    public void btnClick(View v) {
//        EditText edt = findViewById(R.id.edText);
//        Editable ed = edt.getText();
//        String strName = ed.toString();
//
//        TextView strView = findViewById(R.id.textV);
//        strView.setText(strName);
//    }

}