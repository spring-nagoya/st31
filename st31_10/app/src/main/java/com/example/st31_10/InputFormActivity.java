package com.example.st31_10;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class InputFormActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private SQLiteDatabase db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_input_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        try {
            dbHelper = new DatabaseHelper(this);
            db = dbHelper.getWritableDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

        clearForm();

        Button btnReturn = findViewById(R.id.btnReturn);
        Button btnClear = findViewById(R.id.btnClear);
        Button btnInsert = findViewById(R.id.btnInsert);

        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });



    }
    public void clearForm() {
        EditText inId = findViewById(R.id.inputName);
        inId.setText("");
        ((EditText)findViewById(R.id.inputName)).setText("");

        EditText inUrl = findViewById(R.id.inputUrl);
        inUrl.setText("");

    }
    public void insertData() {
        EditText inId = findViewById(R.id.inputId);
        String id = inId.getText().toString();


        EditText inName = findViewById(R.id.inputName);
        String name = inName.getText().toString();

        EditText inUrl = findViewById(R.id.inputUrl);
        String url = inUrl.getText().toString();


        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("name", name);
        values.put("url", url);
        db.insert("mod", null, values);

//        UserBeans mod = new UserBeans(id, name, Integer.parseInt(url), pass);
//        mod.fncGetData(InputFormActivity.this);
////        strId = mod.getId();
//        mod.fncGetData(InputFormActivity.this, R.id.inputId);

        Toast.makeText(this, "データを登録しました", Toast.LENGTH_SHORT).show();





//        String strSQL = "in

        clearForm();
    }
}