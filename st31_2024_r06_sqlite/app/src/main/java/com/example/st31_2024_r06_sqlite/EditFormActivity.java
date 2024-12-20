package com.example.st31_2024_r06_sqlite;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
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

import java.util.ArrayList;
import java.util.HashMap;

public class EditFormActivity extends AppCompatActivity {

    //DBの変数宣言
    private DatabaseHelper dbHelper;
    private SQLiteDatabase mydb;
    private String exrId;

    ArrayList<HashMap<String, String>> ary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_form);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.upd_main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        dbHelper = new DatabaseHelper(this);
        mydb = dbHelper.getWritableDatabase();



        fncClear();

        exrId = getIntent().getStringExtra("id");

        ary = fncSQLite(exrId);
        for(int i = 0; i < ary.size(); i++){
            HashMap<String, String> map = ary.get(i);
            ((EditText)findViewById(R.id.upd_inputId)).setText(map.get("id"));
            ((EditText)findViewById(R.id.upd_inputName)).setText(map.get("name"));
            ((EditText)findViewById(R.id.upd_inputAge)).setText(map.get("age"));
            ((EditText)findViewById(R.id.upd_inputPass)).setText(map.get("pass"));

        }

        //戻るボタンを押した時
        Button btnReturn = findViewById(R.id.upd_btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //クリアボタンを押した時
        Button btnClear = findViewById(R.id.upd_btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力エリアのクリア
                fncClear();
            }
        });

        //更新ボタンを押した時
        Button btnUpdate = findViewById(R.id.upd_btnUpdate);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //入力エリアの値受け取り
                EditText inId = findViewById(R.id.upd_inputId);
                String strId = inId.getText().toString();

                EditText inName = findViewById(R.id.upd_inputName);
                String strName = inName.getText().toString();

                EditText inAge = findViewById(R.id.upd_inputAge);
                String strAge = inAge.getText().toString();

                EditText inPass = findViewById(R.id.upd_inputPass);
                String strPass = inPass.getText().toString();

                //入力検証
                if(strId.isEmpty() || strName.isEmpty() || strAge.isEmpty() || strPass.isEmpty()){
                    //画面にinsertの通知(Toast)
                    Toast.makeText(EditFormActivity.this,
                            "未入力の項目があります",Toast.LENGTH_LONG).show();
                    return;
                }

                // mydb.execSQLでupdate文を作って渡す。
//                String strSQL = "update user set name = '" + strName + "', age = " + strAge + ", pass = '" + strPass + "' where id = '" + strId + "'";
//                mydb.execSQL(strSQL);

                //ContentValuesを使って、データ登録の準備 12/18続き)
                ContentValues values = new ContentValues();
                values.put("name",strName);
                values.put("age",strAge);
                values.put("pass",strPass);

                //mydb.updateでデータ登録をさせる。
                mydb.update("user",values,"id = ?",new String[]{strId});

                //画面にinsertの通知(Toast)
                Toast.makeText(EditFormActivity.this,
                        "updateが完了",Toast.LENGTH_LONG).show();

//                画面終了
                finish();
            }
        });

    }

    //入力エリアのクリア関数
    public void fncClear(){
        EditText inId = findViewById(R.id.upd_inputId);
        inId.setText("");

        ((EditText)findViewById(R.id.upd_inputName)).setText("");

        EditText inAge = findViewById(R.id.upd_inputAge);
        inAge.setText("");

        EditText inPass = findViewById(R.id.upd_inputPass);
        inPass.setText("");
    }

    @SuppressLint("Range")
    public ArrayList<HashMap<String, String>> fncSQLite(String keyword){
        Cursor dbRows;

        dbRows = mydb.query("user", new String[]{"id","name","age","pass"}, "id like ?", new String[]{ exrId }, null, null, null);

        int row_count = dbRows.getCount();
        if(row_count == 0){
            Toast.makeText(EditFormActivity.this,
                    "データがありません",Toast.LENGTH_LONG).show();
        } else {
            dbRows.moveToFirst();
            ary = new ArrayList<>();
            for(int i = 0; i < row_count; i++){
                HashMap<String, String> map = new HashMap<>();
                map.put("id", dbRows.getString(dbRows.getColumnIndex("id")));
                map.put("name", dbRows.getString(dbRows.getColumnIndex("name")));
                map.put("age", dbRows.getString(dbRows.getColumnIndex("age")));
                map.put("pass", dbRows.getString(dbRows.getColumnIndex("pass")));
                ary.add(map);
                dbRows.moveToNext();
            }
        }
        dbRows.close();
        return ary ;
    }

}