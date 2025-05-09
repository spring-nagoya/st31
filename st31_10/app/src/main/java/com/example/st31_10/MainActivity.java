package com.example.st31_10;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.example.st31_10.R;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

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
            e.printStackTrace();
        }
        db = helper.getWritableDatabase();


        AbstractList<HashMap<String, String>> ary = fncSQLite(db, "");
        //該当データをLinearLayoutに表示
        fncDataDisp(ary);

//            Snackbar.make(binding.getRoot(), strMessage, Snackbar.LENGTH_LONG).show();
        Button btnSearch = findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db = helper.getWritableDatabase();
                EditText edKey = findViewById(R.id.edKeyword);
                String strKey = edKey.getText().toString();
                AbstractList<HashMap<String, String>> ary = fncSQLite(db, strKey);
                fncDataDisp(ary);
            }
        });
        Button btnInsert = findViewById(R.id.btnAdd);
        btnInsert.setOnClickListener(new View.OnClickListener() {
            final Intent intent = new Intent(MainActivity.this, InputFormActivity.class);

            @Override
            public void onClick(View v) {
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        EditText edKey = findViewById(R.id.edKeyword);
        String strKey = edKey.getText().toString();

        fncSQLite(db, strKey);
        fncDataDisp(fncSQLite(db, strKey));
    }

    @SuppressLint("Range")
    public AbstractList<HashMap<String, String>> fncSQLite(SQLiteDatabase db, String strKey) {
        Cursor dbRows;
        try {
            if (!Objects.equals(strKey, "")) {
                dbRows = db.rawQuery("select * from mod where name like ?", new String[]{"%" + strKey + "%"});
            } else {
                dbRows = db.rawQuery("select * from mod", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        dbRows = db.query("t_mod",
//        new String[]{"id","name","age","pass"},
//                "name like ?",
//                new String[]{ "%" +strKey + "%" },
//        null,
//        null,
//        null);

        String strMessage = "";
        TextView textV = findViewById(R.id.textView1);

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

                map.put("url", dbRows.getString(dbRows.getColumnIndex("url")));

                ary.add(map);
                dbRows.moveToNext();
            }
            dbRows.close();
            textV.setText(strMessage);
            return ary;
        }

        textV.setText(strMessage);
        dbRows.close();
        return null;
    }

    public void fncDataDisp(AbstractList<HashMap<String, String>> ary) {
        LinearLayout linear1 = findViewById(R.id.linearRow);

        //LinearLayoutの中のデータを削除

        linear1.removeAllViews();

        //1件分の表示ループ
        if (ary == null) {
            return;
        }
        for (HashMap map : ary) {

            View view_row = getLayoutInflater().inflate(R.layout.row_data, null);

            linear1.addView(view_row);

            TextView txtData = view_row.findViewById(R.id.txtData);

            txtData.setText("id:" + map.get("id") + ",name:" + map.get("name"));

            Button btnUpdate = view_row.findViewById(R.id.btnUpdate);

            btnUpdate.setTag(map.get("id"));
            btnUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strId = (String) v.getTag();
                    Intent intent = new Intent(MainActivity.this, EditFormActivity.class);
                    intent.putExtra("id", strId);
                    startActivity(intent);

                }
            });

            Button btnDelete = view_row.findViewById(R.id.btnDelete);

            btnDelete.setTag(map.get("id"));
            btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String strId = (String) v.getTag();
                    db.delete("mod", "id = ?", new String[]{strId});
                    fncDataDisp(fncSQLite(db, ""));
                }
            });

        }
    }

    public static class SQLResult {
        public ArrayList<HashMap<String, String>> ary;
        public String strMessage;

        public void Result(ArrayList<HashMap<String, String>> value1, String value2) {
            this.ary = value1;
            this.strMessage = value2;
        }
    }

    @SuppressLint("Range")
    public void fncSQLite(String keyword){
        Cursor dbRows;
        try {
            if (!Objects.equals(keyword, "")) {
                dbRows = db.rawQuery("select * from mod where name like ?", new String[]{"%" + keyword + "%"});
            } else {
                dbRows = db.rawQuery("select * from mod", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        String strMessage = "";
        TextView textV = findViewById(R.id.textView1);
        int row_count = dbRows.getCount();
        if (row_count == 0) {
            strMessage = "データがありません";
        } else {
            dbRows.moveToFirst();
            ArrayList<HashMap<String, String>> ary = new ArrayList<>();
            for (int i = 0; i < row_count; i++) {
                int colIndexName = dbRows.getColumnIndex("name");
                String strName = dbRows.getString(colIndexName) + "\n";
                @SuppressLint("Range")
                String strId = dbRows.getString(dbRows.getColumnIndex("id"));
                strMessage += "ID:" + strId + ", 名前:" + strName + "\n";
                HashMap<String, String> map = new HashMap<>();
                map.put("id", dbRows.getString(dbRows.getColumnIndex("id")));
                map.put("name", dbRows.getString(dbRows.getColumnIndex("name")));
                map.put("url", dbRows.getString(dbRows.getColumnIndex("url")));
                ary.add(map);
                dbRows.moveToNext();
            }
            dbRows.close();
            textV.setText(strMessage);
            fncDataDisp(ary);
        }
        textV.setText(strMessage);
        dbRows.close();
    }

}


//    @Override
//    public boolean onSupportNavigateUp() {
//        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
//        return NavigationUI.navigateUp(navController, appBarConfiguration)
//                || super.onSupportNavigateUp();
//    }
//}