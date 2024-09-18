package com.example.st31_2024_r06_sqlite;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.st31_2024_r06_sqlite.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;



public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

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
        try {
            helper = new DatabaseHelper(this);
        } catch (Exception e) {}
        db = helper.getWritableDatabase();
        Cursor dbRows;
        dbRows = db.rawQuery("select * from user", null);


//        dbRows = db.query("t_user",
//        new String[]{"id","name","age","pass"},
//        null,
//        null,
//        null,
//        null,
//        null);
        String strMessage = "";
        int row_count = dbRows.getCount();
        if(row_count == 0){
            strMessage = "データがありません";
        } else {
            dbRows.moveToFirst();
            for (int i = 0; i < row_count; i++) {
                int colIndexName = dbRows.getColumnIndex("name");
                String strName = dbRows.getString(colIndexName) + "\n";
                @SuppressLint("Range")
                String strId = dbRows.getString(dbRows.getColumnIndex("id"));

                strMessage += "ID:" + strId + ", 名前" + strName + "\n";
                dbRows.moveToNext();
            }
            TextView textV = findViewById(R.id.textView1);
            textV.setText(strMessage);
//            Snackbar.make(binding.getRoot(), strMessage, Snackbar.LENGTH_LONG).show();
        }

        dbRows.close();
        db.close();

//        setSupportActionBar(binding.toolbar);

//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

//        binding.fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAnchorView(R.id.fab)
//                        .setAction("Action", null).show();
//            }
//        });
    }
}

//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//}