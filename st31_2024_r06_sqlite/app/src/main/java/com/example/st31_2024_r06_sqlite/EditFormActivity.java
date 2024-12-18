package com.example.st31_2024_r06_sqlite;


import android.annotation.SuppressLint;
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

import com.example.st31_2024_r06_sqlite.DatabaseHelper;
import com.example.st31_2024_r06_sqlite.R;

public class EditFormActivity extends AppCompatActivity {

    //DBの変数宣言
    private DatabaseHelper dbHelper;
    private SQLiteDatabase mydb;

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

//        intentで受け取った値を表示
//        String strId = getIntent().getStringExtra("id");

        fncClear();

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

}