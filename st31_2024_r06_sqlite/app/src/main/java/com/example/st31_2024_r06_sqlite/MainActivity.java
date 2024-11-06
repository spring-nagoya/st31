package com.example.st31_2024_r06_sqlite;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.st31_2024_r06_sqlite.databinding.ActivityMainBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private DatabaseHelper helper;
    private SQLiteDatabase db;

    @SuppressLint("Range")
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
        } catch (Exception e) {

        }
        db = helper.getWritableDatabase();


        AbstractList<HashMap<String, String>> ary =fncSQLite(db);
            //該当データをLinearLayoutに表示
        fncDataDisp(ary, strMessage);

//            Snackbar.make(binding.getRoot(), strMessage, Snackbar.LENGTH_LONG).show();
        Button btnSearch = findViewById(R.id.btnSearch);
            btnSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }



@SuppressLint("Range")
public AbstractList<HashMap<String, String>> fncSQLite(SQLiteDatabase db) {

    Cursor dbRows;
    dbRows = db.rawQuery("select * from user", null);


//        dbRows = db.query("t_user",
//        new String[]{"id","name","age","pass"},
//        null,
//        null,
//        null,
//        null,
//        null);
//        LinearLayout inflater = La
    String strMessage = "";
    int row_count = dbRows.getCount();
    if (row_count == 0) {
        strMessage = "データがありません";
    } else {
        dbRows.moveToFirst();
        ArrayList<HashMap<String, String>> ary
                = new ArrayList<>();
        for (int i = 0; i < row_count; i++) {
            int colIndexName = dbRows.getColumnIndex("name");
            String strName = dbRows.getString(colIndexName) + "\n";
            @SuppressLint("Range")
            String strId = dbRows.getString(dbRows.getColumnIndex("id"));

            strMessage += "ID:" + strId + ", 名前:" + strName + "\n";
            HashMap<String, String> map = new HashMap<>();

            map.put("id", dbRows.getString(dbRows.getColumnIndex("id")));

            map.put("name", dbRows.getString(dbRows.getColumnIndex("name")));

            map.put("age", dbRows.getString(dbRows.getColumnIndex("age")));
            map.put("pass", dbRows.getString(dbRows.getColumnIndex("pass")));
            ary.add(map);
            dbRows.moveToNext();

            dbRows.close();
            db.close();
            return ary;
        }
    }
    return null;
}

    public void fncDataDisp(ArrayList<HashMap> ary, String strMessage) {
        LinearLayout linear1 = findViewById(R.id.linearRow);

        //LinearLayoutの中のデータを削除

        linear1.removeAllViews();

        //1件分の表示ループ

        for (HashMap map : ary) {

            View view_row = getLayoutInflater().inflate(R.layout.row_data, null);

            linear1.addView(view_row);

            TextView txtData = view_row.findViewById(R.id.txtData);

            txtData.setText("id:" + map.get("id") + ",name:" + map.get("name"));

            Button btnUpdate = findViewById(R.id.btnUpdate);

            btnUpdate.setTag(map.get("id"));

        }

        TextView textV = findViewById(R.id.textView1);
        textV.setText(strMessage);
    }
    public static class SQLResult {
        public ArrayList<HashMap<String, String>> ary;
        public String strMessage;
        public void Result(ArrayList<HashMap<String, String>> value1, String value2) {
            this.ary = value1;
            this.strMessage = value2;
        }
    }
}


//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//}