package com.example.st31_2024_project;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity
//        implements View.OnClickListener
{

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

        //    匿名クラスを使った方法
        Button button;
        button = findViewById(R.id.btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt = findViewById(R.id.edText);
                Editable ed = edt.getText();
                String strName = ed.toString();

                TextView strView = findViewById(R.id.textV);
                strView.setText(strName);

//                課題No.2

                EditText editText = findViewById(R.id.edText2);
                String strText2 = editText.getText().toString();
                Integer intText = 0;
                Integer intText2 = 0;

                boolean flg = true;
                try {
                    intText2 = Integer.valueOf(strText2);
                    intText = Integer.valueOf(strName);

                } catch (NumberFormatException e){
                    Log.w("st31_2024_r01", "数値以外の入力");
                    flg = !flg;
                } catch (Exception e){
                    Log.e("st31_2024_r01", Objects.requireNonNull(e.getMessage()));
                    flg = !flg;
                }
//                TextView strView = findViewById(R.id.textV);
                String strMessage = "入力エリアに文字あり";
                if(flg){
                    Integer intTotal = 0;
                    intTotal = intText + intText2;
                    strMessage = intTotal.toString();
//                    strView.setText(intTotal);
                }else {
                    strMessage = "入力エリアに文字あり";
                }
                strView.setText(strMessage);

            }

        });
    }

//    方法2
//




    
//    public void btnClick(View v) {
//        EditText edt = findViewById(R.id.edText);
//        Editable ed = edt.getText();
//        String strName = ed.toString();
//
//        TextView strView = findViewById(R.id.textV);
//        strView.setText(strName);
//    }

}